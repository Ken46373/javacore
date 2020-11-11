# javacore
 

---------------------------------------------------------

## 🚀 javacore

### ✈️ 概要<br>
    Java コアパッケージ (Servlet + Utils)
    言語:       Java
    WEBコンテナ: Tomcat
    DB:         PostgreSQL

### ✈️ パッケージ構成<br>
    javacore
        - data
            - AbstractAccessor.java
            - DatabaseConnectionFactory.java
            - DatabaseTransaction.java
            - DualAccessor.java
            - SqlExecuter.java
        - mail
            - MailSender.java
        - presentations
            - annotations
                - ParamMapping.java
                - Urlmapping.java
            - filters
                - Filter.java
            - views
                - FileView.java
                - HtmlView.java
                - JsonView.java
                - RedirectView.java
                - StatusView.java
                - StringView.java
                - View.java
            - Controller.java
            - DispatcherServlet.java
            - HttpMethod.java
            - RequestHandler.java
        - settings
            - DatabaseSettings.java
            - MailSettings.java
            - MailTemplate.java
            - SettingsParser.java
        - utils
            - DateUtility.java
            - StringUtility.java
            - ValidationUtility.java

### ✈️ 依存ライブラリ<br>
    ・Java Servlet API
    ・JavaServer Pages API
    ・JSTL
    ・Gson
    ・JavaMail API
    ・Apache log4j Core
    ・Apache log4j API
    ・Apache Commons Validator
    ・PostgreSQL JDBC Driver

### ✈️ 起動時処理<br>
    Tomcat -> DispatcherServlet#contextInitialized

### ✈️ HTTPリクエスト受信時処理<br>
    1. Tomcat (service/doGet/doPost/doPut/doDelete)
    2. DispatcherServlet
        2.1 Controllerが存在しない場合 -> forward(DefaultServletにディスパッチ)
        2.2 Controllerが存在する場合   ->
            2.2.1 Controller preProcess/getFilters
            2.2.2 Filter     doFilter
            2.2.3 Controller invoke/postProcess
            2.2.4 View       render

---------------------------------------------------------
