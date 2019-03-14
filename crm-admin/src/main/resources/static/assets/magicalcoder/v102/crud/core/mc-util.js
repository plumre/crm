/**
 *  工具类
 */

layui.define(['jquery','form'],function(exports){
    var form = layui.form,$=layui.jquery;
    var obj = {
        //获取浏览器入参
        getParameter:function (name) {
            var query = window.location.search.substring(1);
            if(query!=''){
                var vars = query.split("&");
                for (var i=0;i<vars.length;i++) {
                    var pair = vars[i].split("=");
                    if(pair.length=2){
                        if(pair[0] == name){return pair[1];}
                    }
                }
            }
            return null;
        },
        escapeHTML: function(a){//layui/table.js已经被改动 无法顺利升级
            if(!a || a==null){
                return '';
            }
            a = "" + a;
            return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;");
        },
        mappingEntityToFormUiValue : function (elem,entityData) {//映射实体的值到界面元素中
            var body = elem;
            if(entityData){
                for(var key in entityData){
                    var value = entityData[key]
                    if(value != null){
                        var input = body.find("[name='"+key+"']");
                        if(!input){
                            continue;
                        }
                        var laySkin = input.attr("lay-skin")
                        if(laySkin && laySkin == 'switch'){//忽略开关
                            continue;
                        }
                        //外键select2
                        var className = input.attr("class")
                        if(className && className.indexOf("magicalcoder-foreign-select2")!=-1){
                            input.attr("data-value",value);
                            continue;
                        }
                        //设置radio值
                        var type = input.attr("type")
                        if(type && type=='radio' && value && value!=''){
                            body.find("[name='"+key+"'][value='" + value + "']").prop("checked", "checked");
                            continue
                        }
                        //如果有图片或者文件则自动设置图片
                        var inputImg = body.find(".img_"+key);
                        if(inputImg){
                            inputImg.attr("src",value);
                        }
                        //其他正常表单
                        input.val(value);
                    }
                }
                //开关单独处理
                body.find("input[lay-skin='switch']").each(function (index,item) {
                    var name = $(item).attr("name")
                    var check = (entityData[name]+''=='true' || entityData[name]+''=='1') ? true:false
                    $(item).prop("checked",check);
                })
                form.render();
            }
        },
        
    }

    exports('mc_util',obj);
})
