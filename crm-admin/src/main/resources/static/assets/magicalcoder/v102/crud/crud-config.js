var jsVersion="v102"
layui.config({
    base : "assets/"
}).extend({
    //lib包
    "authtree" : "magicalcoder/"+jsVersion+"/crud/lib/authtree",
    "select2" : "magicalcoder/"+jsVersion+"/crud/lib/select2",
    //core包
    "mc_constant" : "magicalcoder/"+jsVersion+"/crud/core/mc-constant",
    "mc_util" : "magicalcoder/"+jsVersion+"/crud/core/mc-util",
    "mc_verify":"magicalcoder/"+jsVersion+"/crud/core/mc-verify",
    //component
    "mc_layui_component":"magicalcoder/"+jsVersion+"/crud/component/mc-layui-component",
    "mc_children":"magicalcoder/"+jsVersion+"/crud/component/mc-children",
    "mc_rmp":"magicalcoder/"+jsVersion+"/crud/component/mc-rmp",
    "mc_select2":"magicalcoder/"+jsVersion+"/crud/component/mc-select2"
})
