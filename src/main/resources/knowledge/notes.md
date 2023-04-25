# 踩坑
1. MybatisX 自动生成的 Mapper 没有 @Mapper 注解
    要么手动在 Mapper 中添加 @Mapper 注解 要么在启动类中添加 @MapperScan 注解并指明扫描包路径