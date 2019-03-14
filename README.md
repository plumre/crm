当前版本 - v101

crm是crm系列框架，默认数据库引擎采用mysql,如需要其他数据库，请至www.magicalcoder.com下载对应引擎
配置模板时 请设置 模板版本=crm

1 如何启动
    管理后台：crm-admin
        
        安装环境
            框架需要redis服务，请自行下载或者双击project/redis/redis-启动.bat
        配置环境
           crm\crm-admin\src\main\resources\application-publish.yml
           请根据实际情况配置 mysql redis
        导入数据库
            crm\crm.sql 导入上面配置的数据库
        运行
            crm\crm-admin\src\main\java\com\cahodental\crm\admin\CrmAdminApplication.java
            main函数即可启动
        
        也可以打成jar启动也行
        方式1 java -jar crm-admin.jar
        方式2 nohup>nohupGps java -jar crm-admin.jar --spring.profiles.active=deploy 2>&1 &
        
        启动成功后 浏览器访问
        http://localhost:18080/crm-admin
            默认 admin/admin登陆
        
2 声明
    crm 为开源项目，会持续不断的更新，在使用过程中遇到问题 欢迎联系作者 QQ 799374340

3 常见问题：
    1 验证码无法使用 null报错 解决方法：请更换最新jdk1.8版本 建议多尝试几个版本的 即可解决

无缝简单升级方法 
1 升级java 
    更新crm最新包，把crm-core覆盖您本地，前提是您未修改crm-core已经存在的类
2  升级js html
    更新crm最新包，把
     crm-admin\src\main\resources\static\assets\magicalcoder\vxxx 
     crm-admin\src\main\resources\templates\magicalcoder\vxxx
     覆盖您本地
3 升级模板
    magicalcoder软件包soft\vms\crm_xxx会发布最新版本模板，更换至最新模板即可

当然如果您不希望进行任何升级 无需改动任何内容

版本更新记录：您可以根据记录来升级您当前的版本 查看版本在 /crm_mysql/README.md 如果不想升级 请做好老版本vms下的模板备份
#v101 - 2019/1/15
## 1 支持excel导出功能 
## 2 重构js模块，之前版本限制太多，现在完全开放，更大程度满足扩展性
## 变更文件记录  您可以根据此文件来进行手动更新老版本 使用idea文件差异（Ctrl选中2文件夹 Ctrl+D）对比即可完成更新
        modified:   pom.xml
        modified:   crm-core/pom.xml
        modified:   crm-core/src/main/java/com/magicalcoder/crm/core/common/dto/KeyValue.java
        modified:   crm-core/src/main/java/com/magicalcoder/crm/core/service/CommonRestController.java
        new file:   crm-core/src/main/java/com/magicalcoder/crm/core/utils/POIUtil.java
        new folder: crm-admin/src/main/resources/templates/magicalcoder/v101/
        new folder: crm-admin/src/main/resources/static/assets/magicalcoder/v101/

## 开发文档
    ##1 如何自定义新页面 并配置跳转规则
    我们拿goods_category表来讲解
    templates/goodscategory/goodscategory-edit.html
    templates/goodscategory/goodscategory-list.html
    注意goodscategory文件夹要小写
    
    URL规则/admin/page/{tableName}/{editOrList}
    跳转规则由 AdminRmpController.mapping来定制规则
        return {tableName}.replaceAll("_","") +"/"+ {tableName}.replaceAll("_","") +"-"+ {editOrList};
    示例：/admin/page/goods_category/list
    goods_category中的_会被替换成空 然后映射到对应文件夹 和对应是页面
