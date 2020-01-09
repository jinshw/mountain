# golang实现shape属性编辑工具

## 需求

* 实现shape文件属性列表展示
* 导出shape属性类表为Excel
* 通过关联Excel表字段，新增shape字段

## 分析

* 读取shape文件：使用第三方包`github.com/jonas-p/go-shp@v0.1.1`
* excel文件操作：使用第三方包`github.com/tealeg/xlsx`
* 页面使用HTML布局：后端web框架使用gin，前端使用layerui

## 工程目录结构

├─excel
├─html
├─shape
├─src
│  └─main
└─static

* excel目录：需要关联读取的excel
* html目录：前端html页面
* shape目录：需要操作的shape
* src目录：源文件
* static目录：静态文件



## 代码解读



### 读取shape

* 读取shape属性表头字段，主要代码如下：

```go
func GetCols(shapefile string) []shp.Field {
    shape, err := shp.Open(shapefile)
    if err != nil {
        fmt.Errorf("UpdateShape err: %v", err)
    }
    defer shape.Close()
    fields := shape.Fields()
    return fields
}
```



* 读取shape属性表数据，主要代码如下：

```go
shape, err := shp.Open(filepath)
if err != nil {
    fmt.Errorf("UpdateShape err: %v", err)
}
defer shape.Close()
fields := shape.Fields()

results := make([]map[string]string, 0)
for shape.Next() {
    n, p := shape.Shape()
    fmt.Println(n, p, fields, startPageNumber)
    if n >= (startPageNumber-1) && n <= (endPageNumber-1) {
        var item map[string]string
        item = make(map[string]string)
        for k, f := range fields {
            val := shape.ReadAttribute(n, k)
            fmt.Println(n, p, k, f, val)
            name := string(f.Name[:])
            name = strings.Trim(strings.Replace(name, "\u0000", "", -1), " ")
            dec := mahonia.NewDecoder(Encoding)
            valutf8 := dec.ConvertString(val)
            item[name] = valutf8
        }
        results = append(results, item)
    }
    if n >= endPageNumber {
        break
    }
}
```



### 导出Excel

* shape数据保存为Excel文件，主要代码如下：

```go
// 读取shape值
shape, err := shp.Open(ShapeFileName)
if err != nil {
    fmt.Errorf("UpdateShape err: %v", err)
}
defer shape.Close()
fields := shape.Fields()

// 新建excel文件
file := xlsx.NewFile()
sheet, _ := file.AddSheet("Sheet1")
// add Header
row := sheet.AddRow()
row.SetHeightCM(1) //设置每行的高度
for k, f := range fields {
    cell := row.AddCell()
    cell.Value = f.String()
    fmt.Println(k)
}

for shape.Next() {
    row := sheet.AddRow()
    row.SetHeightCM(1) //设置每行的高度
    n, p := shape.Shape()

    for k, f := range fields {
        val := shape.ReadAttribute(n, k)
        dec := mahonia.NewDecoder(Encoding)
        val = dec.ConvertString(val)

        fmt.Println(n, p, k, f, val)
        cell := row.AddCell()
        // 去除特殊字符
        getVal := strings.Replace(val, " ", "", -1)
        getVal = strings.Replace(strconv.Quote(getVal), "\x00", "", -1)
        getVal = strings.Replace(getVal, "\"", "", -1)
        getVal = strings.Replace(getVal, "\\x00", "", -1)
        cell.Value = getVal
    }

}
//保存excel文件
errXlsx := file.Save("file.xlsx")
```



* 下载Excel文件

  后端golang主要代码：

```go
func FileDownload(c *gin.Context, filename string) {
	c.Writer.Header().Add("Content-Disposition", fmt.Sprintf("attachment; filename=%s", 		filename)) //fmt.Sprintf("attachment; filename=%s", filename)对下载的文件重命名
	c.Writer.Header().Add("Content-Type", "application/octet-stream")
	c.File(filename)
}
```

​	前端js代码

```javascript
$("#exports").click(function () {
    if(shapename == ""){
        layer.msg("请先选择文件")
    }else{
        window.location.href = "/exports";
    }
});
```



### 字段关联

* 读取Excel文件表头字段

```go
r.GET("/getExcelHead", func(context *gin.Context) {
    filename := context.Query("filename")
    fmt.Println("filename==" + filename)
    type Result struct {
        Code int               `json:"code"`
        Msg  string            `json:"msg"`
        Data map[string]string `json:"data"`
    }
    var data = make(map[string]string)
    data = readExcelHead(filename)
    var result Result
    result.Code = 0
    result.Msg = "success"
    result.Data = data
    context.JSON(http.StatusOK, result)
})
```

* 读取shape属性表头字段：如上`GetCols`函数

* Excel和shape字段关联

![image-20191231111204206](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\image-20191231111204206.png)

* shape新增字段和关联属性值Excel字段

![image-20191231111255853](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\image-20191231111255853.png)

### 新增shape字段

* 新增字段，主要代码如下：

```go
addFields := []shp.Field{}
dictValStr := "#"
for bindKey, bindVal := range obj.AddList {
    fmt.Println(bindKey, bindVal, bindVal["newFiledText"], bindVal["bindFiledText"])
    newFiledText := bindVal["newFiledText"]
    fmt.Println("newFiledText==" + newFiledText)
    addFields = append(addFields, shp.StringField(newFiledText, 64))
    dictValStr = dictValStr + bindVal["bindFiledText"] + "#"
}
...
```



* 遍历shape属性，主要代码如下：

```go
shp.UpdateShape(shapeFile, addFields, func(shape *shp.Reader, shapeNew *shp.Writer) {
    fieldsObj := shape.Fields()
    for shape.Next() {
        n, p := shape.Shape()
        shapeNew.Write(p)
        dictKeyStr = "#"
        for key, val := range obj.JoinObj {
            fmt.Println(key, val)
            shpVal := shape.GetValue(key)
            getVal := strings.Replace(shpVal, " ", "", -1)
            getVal = strings.Replace(strconv.Quote(getVal), "\x00", "", -1)
            getVal = strings.Replace(getVal, "\"", "", -1)
            getVal = strings.Replace(getVal, "\\x00", "", -1)
            dictKeyStr = dictKeyStr + getVal + "#"
        }
        for k, f := range fieldsObj {
            val := shape.ReadAttribute(n, k)
            dec := mahonia.NewDecoder(Encoding)
            val = dec.ConvertString(val)
            shapeNew.WriteAttribute(n, k, val)
            fmt.Println(k, f)
        }
        teampBindField := dictValStr[1 : len(dictValStr)-1]
        bindFileds := strings.Split(teampBindField, "#")
        for idx := 0; idx < len(bindFileds); idx++ {
            bindFiled := bindFileds[idx]
            newVal := DictData[dictKeyStr][bindFiled]
            shapeNew.WriteAttribute(n, len(fieldsObj)+idx, newVal)
        }
    }
})
```



### 保存shape文件

* 上面函数`shp.UpdateShape` 是在第三方go-shp 库扩展而来，主要实现生成新临时shape文件，并把这个临时shape文件替换原本的文件。主要代码如下：

```go
func UpdateShape(src string, addFields []Field, callback func(shape *Reader, shapeNew *Writer)) {
	var dist string = time.Now().Format("teap_20060102150405") + ".shp"
	shape, err := Open(src)
	if err != nil {
		fmt.Errorf("UpdateShape err: %v", err)
	}
	defer shape.Close()

	// fields to write
	fields := shape.Fields()

	fields = append(fields, addFields...)

	//shapeNew, errNew := shp.Append("test.shp")
	shapeNew, errNew := Create(dist, POINT)
	if errNew != nil {
		fmt.Errorf("UpdateShape err: %v", errNew)
	}
	defer shapeNew.Close()
	shapeNew.SetFields(fields)
	//回调函数
	callback(shape, shapeNew)
	shape.Close()
	shapeNew.Close()
	distarr := strings.Split(dist, ".")
	distName := distarr[0]
	srcarr := strings.Split(src, ".")
	srcName := srcarr[0]
	if len(srcarr) > 2 { // 说明路径中有多个.
		srcName = "."
		for i := 0; i < len(srcarr)-1; i++ {
			srcName = srcName + srcarr[i]
		}
	}
	shpSrc := srcName + ".shp"
	dbfSrc := srcName + ".dbf"
	shxSrc := srcName + ".shx"
	cpgSrc := srcName + ".cpg"
	shpDist := distName + ".shp"
	dbfDist := distName + ".dbf"
	shxDist := distName + ".shx"

	Copy(shpDist, shpSrc)
	Copy(dbfDist, dbfSrc)
	Copy(shxDist, shxSrc)
	//
	cpgFileWrite, err := os.Create(cpgSrc)
	cpgFileWrite.Write([]byte("UTF-8"))

	os.Remove(shpDist)
	os.Remove(dbfDist)
	os.Remove(shxDist)
}
```

### 文件拷贝

```go
func Copy(src string, dist string) {
	//打开源文件
	fileRead, err := os.Open(src)
	//fileRead, err := os.Open("C:/itcase/test-视频.avi")
	if err != nil {
		fmt.Println("Open err:", err)
		return
	}
	defer fileRead.Close()
	//创建目标文件
	fileWrite, err := os.Create(dist)
	if err != nil {
		fmt.Println("Create err:", err)
		return
	}
	defer fileWrite.Close()
	//info, _ := os.Stat(src) //Stat获取文件属性
	//filesize := info.Size()

	//从源文件获取数据，放到缓冲区
	buf := make([]byte, 4096)
	//循环从源文件中获取数据，全部写到目标文件中
	for {
		n, err := fileRead.Read(buf)
		if err != nil && err == io.EOF {
			//getProgress(src, filesize, n)
			fmt.Printf("读取完毕，n = d%\n:", n)
			return
		}
		fileWrite.Write(buf[:n]) //读多少、写多少
		//getProgress(src, filesize, n)
	}
}
```



## 工具操作

* 启动: 双击`main.exe`
* 访问：在浏览器中访问http://localhost:8080/index/
* 读取shape列表：点击左侧文件列表中的shape文件，右侧表格展示shape文件属性列表。如果表格中文乱码，请切换下拉列表编码集

![image-20191231112601233](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\image-20191231112601233.png)

* 导出excel文件：点击`导出`能导出表格数据

![image-20191231112745716](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\image-20191231112745716.png)

* 关联新增：点击`关联新增`按钮，在弹出页面中，选择要的excel文件，关联shape和excel字段；添加新增字段和对应的绑定字段（即excel中的字段），最后点击`保存`按钮。在执行完毕后，刷新页面读取shape列表，检查是否正确。

![image-20191231112946743](E:\项目\学习项目\自己博客文章\myblogmarkdown\imgs\image-20191231112946743.png)



## 工程地址

* [shape编辑工具工程](https://github.com/jinshw/shapeedit.git)

* [go-shp第三方工程扩展](https://github.com/jinshw/go-shp.git)