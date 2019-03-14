/**
 *  结合layui的所有控件 统一处理
 *  修改控件值 触发ajax update操作
 *  v 0.0.1
 */

layui.define(['jquery','form','layer','layedit','laydate','upload','slider','colorpicker','rate','code','mc_select2','mc_verify',"mc_constant"],function(exports){
    var form = layui.form,$ = layui.jquery,
    layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        slider = layui.slider,
        rate = layui.rate,
        colorpicker = layui.colorpicker,
        layedit = layui.layedit,
        laydate = layui.laydate,
        mc_verify = layui.mc_verify,
        mc_constant = layui.mc_constant,
        mc_select2 = layui.mc_select2 ;
//自定义验证规则
    var obj = {
        //绘制详情页layui各种组件 并且会预设初始值
        bindMagicalCoderLayUiComponentFromDetail:function (config) {
            var _t = this;
            this.config = config;
            //寻找所有日期控件 自动注册
            this._bindDate("."+mc_constant.MC_LAY_CLASS_NAME.FORM.laydate);
            //寻找所有图片上传
            this._bindUpload("."+mc_constant.MC_LAY_CLASS_NAME.FORM.layupload);
            //外键下拉
            $("."+mc_constant.MC_LAY_CLASS_NAME.FORM.foreign_select2).each(function (index,item) {
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                if(id){
                    _t._bindForeignSelect2(config.tableNameRest,"#"+id,{width:"100%"});
                }else {
                    _t._bindForeignSelect2(config.tableNameRest,"[name='"+name+"']",{width:"100%"});
                }
            })
            //寻找滑块
            this._bindSlider("."+mc_constant.MC_LAY_CLASS_NAME.FORM.slider);
            //寻找颜色选择器
            this._bindColorPicker("."+mc_constant.MC_LAY_CLASS_NAME.FORM.color_picker);
            //寻找评星
            this._bindRate("."+mc_constant.MC_LAY_CLASS_NAME.FORM.rate);
            //寻找代码修饰器
            this._bindCode("."+mc_constant.MC_LAY_CLASS_NAME.FORM.code);
            //寻找所有的富文本控件
            var formVerifyEditData = this._bindEdit("."+mc_constant.MC_LAY_CLASS_NAME.FORM.layedit);
            return formVerifyEditData;
        },
        //绘制table中layui各种组件 并且会预设初始值
        bindMagicalCoderLayUiComponentFromTable:function (config,failCallBack) {
            this.config = config;
            var configData = {width:"200px",allowClear:false}
            this._bindSingleSelect2(config.tableNameRest,"."+mc_constant.MC_LAY_CLASS_NAME.TABLE.single_select2,configData,failCallBack)
            this._bindForeignSelect2(config.tableNameRest,"."+mc_constant.MC_LAY_CLASS_NAME.TABLE.foreign_select2,configData,failCallBack);
            this._bindSwitchAjaxUpdate("."+mc_constant.MC_LAY_CLASS_NAME.TABLE.lay_switch,failCallBack);
            this._bindDate("."+mc_constant.MC_LAY_CLASS_NAME.TABLE.lay_date,failCallBack);
            this._bindInputText('.'+mc_constant.MC_LAY_CLASS_NAME.TABLE.text,failCallBack);
            this._bindRadio('.'+mc_constant.MC_LAY_CLASS_NAME.TABLE.radio,failCallBack);
        },
        //绘制查询区域layui各种组件 并且会预设初始值 一般也就只有日期跟外键 其他的暂不考虑了 您也可以扩展
        bindMagicalCoderLayUiComponentFromQuery:function (config) {
            this.config = config;
            //绑定外键查询
            this._bindForeignSelect2(config.tableNameRest,"."+mc_constant.MC_LAY_CLASS_NAME.FORM.foreign_select2,{width:"resolve",allowClear:true});
            //绑定日期
            this._bindDate("."+mc_constant.MC_LAY_CLASS_NAME.FORM.laydate);
        },
        _bindRadio : function (elem,clickFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            $(elem).each(function (index, item) {
                var name = $(item).attr("name")//由于radio name不能重复 所以用_+行号来区分
                var arr = name.split("_");
                name = arr[0]
                var id = $(item).attr("id")
                var filter = $(item).attr("lay-filter")
                var select = name ? name : id
                form.on('radio('+filter+')', function(domData){
                    var _t = $(this)
                    var identify = _t.attr("data-identify")
                    if(identify && identify!=''){//ajax更新结果
                        var value = domData.elem.value;
                        var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                        var postData = {}//必须设置主键
                        postData[select] = value;
                        if(!mc_verify.verify(value,$(item))){
                            return;
                        }
                        $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                            layer.close(index);
                            if(data.flag){
                                layer.msg("修改成功！");
                            }else {
                                layer.msg("修改失败:"+data.desc);
                                if(clickFailCallback){
                                    clickFailCallback()
                                }
                            }
                            form.render('radio');//这一步必须执行 否则改了不生效
                        })
                    }
                })
            })
        },
        //普通输入框 输入完成离开事件
        _bindInputText : function (inputElem,failCallBack) {
            var tableNameRest = this.config.tableNameRest;
            $(inputElem).each(function (index,item) {//必须1个个绑定 不支持单利
                var originValue = ''
                $(item).focus(function () {
                    originValue =$(this).val()
                })
                $(item).blur(function () {
                    var newValue = $(this).val()
                    if(originValue != newValue){//需要更新
                        var identify = $(item).attr("data-identify")
                        if(identify && identify!=''){
                            var name = $(item).attr("name")
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}
                            postData[name] = newValue;
                            if(!mc_verify.verify(newValue,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败:"+data.desc);
                                    $(item).val(originValue)//还原
                                }
                            })
                        }
                    }
                })
            })
        },
        //綁定日期控件
        _bindDate : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = {}
                if(formConfig[name]){
                    renderConfig = formConfig[name].renderConfig
                }else {//可能是查询区域
                    var realName = null;
                    var dataName = $(item).attr("data-name")
                    if(dataName && dataName.length>0){
                        realName = dataName
                    }else {//老代码兼容
                        if(name.lastIndexOf("First")!=-1){
                            realName = name.substring(0,name.lastIndexOf("First"))
                        }
                        if(name.lastIndexOf("Second")!=-1){
                            realName = name.substring(0,name.lastIndexOf("Second"))
                        }
                    }
                    if(realName!=null){
                        if(formConfig[realName]){
                            renderConfig = formConfig[realName].renderConfig
                        }
                    }
                }
                var select = id ? "#"+id : "[name='"+name+"']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                if(value && value!=''){//智能点 默认给当前时间做初始值
                    var reg=/^(\d{4})[-\/].*/;
                    if(!reg.test(value)){//非日期字符串格式 尝试解析字符串
                        try {
                            if(value.toUpperCase()=='CURRENT_TIMESTAMP'){
                                value = new Date()
                            }else {
                                value = eval(value)
                            }
                        }catch (e){
                            value = ''
                        }
                    }
                }
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    type : renderConfig['type'] || 'datetime',
                    value : renderConfig['value']|| value,
                    format : renderConfig['format'] || 'yyyy-MM-dd HH:mm:ss',
                    trigger : renderConfig['trigger'] || 'click',
                    done : renderConfig['done'] || function (value,date,endDate) {
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = value;
                            if(!mc_verify.verify(value,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                laydate.render(renderConfig);
            })
        },
        //绑定文件上传组件
        _bindUpload : function (elem,doneFailCallback) {
            var _t = this;
            $(elem).each(function (index,item) {
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {};
                $.extend(true,formConfig,_t.config.form);//深拷贝
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = name ? name : id
                $.extend(renderConfig,{
                    elem: renderConfig['elem'] || '.a_'+select,
                    url: renderConfig['url'] || 'admin/common_file_rest/upload',
                    method: renderConfig['method'] || "post",
                    accept: renderConfig['accept'] || "file",//允许上传普通文件 ，去掉则只支持图片类型
                    before: renderConfig['before'] || function (obj) {
                        layer.load()
                    },
                    done: renderConfig['done'] || function(res, index, upload){
                        layer.closeAll('loading')
                        if(res.flag){
                            var src = res.data.src;
                            $("input[name='"+select+"']").val(src);
                            $(".img_"+select).attr("src",src);
                        }else {
                            layer.alert("上传失败:"+res.desc)
                            if(doneFailCallback){
                                doneFailCallback();
                            }
                        }
                    },
                    error: renderConfig['error'] || function(index, upload){
                        layer.closeAll('loading')
                        layer.alert("上传失败，请重试")
                        if(doneFailCallback){
                            doneFailCallback();
                        }
                    }
                })
                upload.render(renderConfig);
            })
        },
        _bindSingleSelect2 : function (tableNameRest,singleSelect2,select2ConfigData,selectChangeFailCallback) {
            mc_select2.bindTableSingleSelect2(tableNameRest,singleSelect2,select2ConfigData,selectChangeFailCallback);
        },
        _bindForeignSelect2 : function (tableNameRest,foreignSelect2,select2ConfigData,selectChangeFailCallback) {
            mc_select2.bindForeignSelect2ByDomId(tableNameRest,foreignSelect2,select2ConfigData,selectChangeFailCallback);
        },
        _bindSwitchAjaxUpdate : function (elem,doneFailCallback) {//表格中出现也得用 form.on(switch)监听 因为表格暂时没这个处理方法
            var tableNameRest = this.config.tableNameRest;
            $(elem).each(function (index, item) {
                var name = $(item).attr("name")
                var id = $(item).attr("id")
                var filter = $(item).attr("lay-filter")
                var select = name ? name : id
                form.on('switch('+filter+')', function(domData){
                    var _t = $(this)
                    var identify = _t.attr("data-identify")
                    if(identify && identify!=''){//ajax更新结果
                        var targetChecked = domData.elem.checked;
                        var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                        var postData = {}//必须设置主键
                        var value = targetChecked?1:0
                        postData[select] = value;
                        if(!mc_verify.verify(value,$(item))){
                            return;
                        }
                        $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                            layer.close(index);
                            if(data.flag){
                                _t.prop("checked", targetChecked);
                                layer.msg("修改成功！");
                            }else {
                                layer.msg("修改失败:"+data.desc);
                                _t.prop("checked", !targetChecked);
                            }
                            form.render('checkbox');//这一步必须执行 否则改了不生效
                        })
                    }
                })
            })
        },
        //绑定富文本 并返回校验同步框 供表单提交使用
        _bindEdit : function (elem) {
            var formVerifyEditData = {}
            $(elem).each(function (index,item) {
                var id = $(item).attr("id")
                //创建一个编辑器
                var editIndex = layedit.build(id,{
                    height : 300,
                    uploadImage : {
                        type : "post",
                        url : "admin/common_file_rest/upload"
                    }
                });
                //require 校验富文本编辑框时 同步一下隐藏的textarea值 只有在这里同步才生效 VerifyEdit 参见edit.html lay-verify
                formVerifyEditData[id+"VerifyEdit"] = function(val){
                    layedit.sync(editIndex);
                }
            })
            return formVerifyEditData;
        },
        //绑定滑块 不建议在列表页使用
        _bindSlider : function (elem) {
            var _t = this
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {};
                $.extend(true,formConfig,_t.config.form);//深拷贝
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id : "[name='"+name+"Slider']"//列表页有id 所以不会批量绑定

                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    value : renderConfig['value']|| $(item).val(),
                    change : renderConfig['change'] || function (value) {
                        $(item).val(value)
                    }
                })
                slider.render(renderConfig);
            })
        },
        //颜色选择器
        _bindColorPicker : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'ColorPicker' : "[name='"+name+"ColorPicker']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    format : renderConfig['format']|| 'hex',
                    color : renderConfig['color']|| value,
                    done : renderConfig['done'] || function (color) {
                        $(item).val(color)
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = color;
                            if(!mc_verify.verify(color,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                colorpicker.render(renderConfig);
            })
        },
        //评分
        _bindRate : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'Rate' : "[name='"+name+"Rate']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    length : renderConfig['length']|| 5,
                    value : renderConfig['value']|| value,
                    theme : renderConfig['theme']|| '#FFB800',
                    half : renderConfig['half']|| false,
                    text : renderConfig['text']|| false,
                    readonly : renderConfig['readonly']|| false,
                    choose : renderConfig['choose'] || function (val) {
                        $(item).val(val)
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = val;
                            if(!mc_verify.verify(val,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                rate.render(renderConfig);
            })
        },
        //代码修饰器
        _bindCode : function (elem,doneFailCallback) {
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'Code' : "[name='"+name+"Code']"//列表页有id 所以不会批量绑定
                var value = $(item).val()
                var elem = renderConfig['elem'] || select
                $(elem).html(value);
                $.extend(renderConfig,{
                    elem : elem,
                    title : renderConfig['title']|| '',
                    height : renderConfig['height']|| '200px',
                    encode : renderConfig['encode']|| true,
                    skin : renderConfig['skin']|| 'notepad',
                    about : renderConfig['about']|| false
                })
                layui.code(renderConfig);
            })
        }
    }

    exports('mc_layui_component',obj);
})
