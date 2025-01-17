**1. Tìm hiểu framework spring boot.**
   - Spring Boot là một framework web Java mã nguồn mở dựa trên kiến trúc microservice. Framework Spring Boot tạo ra một môi trường tối ưu hóa cho triển khai trong môi trường thực tế, hoàn toàn có thể cấu hình bằng cách sử dụng mã được xây dựng sẵn trong mã nguồn của nó.
   - Spring được xây dựng dựa trên 2 nền tảng là DI(Dependency Injection) và AOP(Aspect Oriented Programming).
     1. Dependency Injection:
        - Dependency Injection (DI) được sử dụng trong lập trình để giảm sự phụ thuộc giữa các lớp và làm cho ứng dụng dễ bảo trì, mở rộng hơn.
        - Có 3 loại dependency injection bao gồm:
          - Constructor Injection: các dependency đc cung cấp thông qua constructor của class.
          - Setter Injection: client tạo ra một setter method để các class khác có thể sử dụng chúng để cấp dependency.
          - Interface injection: dependency sẽ cung cấp một hàm injector để inject nó vào bất kì client nào đc truyền vào. Các client phải implement một interface mà có một setter method dành cho việc nhận dependency.
        - Spring IOC:
          - Là nơi quản lý các bean và thực hiện DI. Các bean được triển khai thông qua XMl hoặc các annotation như: @Component, @Repository, @Service, @Controller, @Bean.
      2. Spring AOP:
         - AOP cho phép bạn định nghĩa aspects và áp dụng chúng vào methods trong ứng dụng mà không cần thay đổi mã nguồn của các lớp đó. Các aspect này có thể thực hiện các hành động trước hoặc sau khi phương thức được gọi.
         - Để định nghĩa một aspect trong Spring, bạn có thể sử dụng annotation @Aspect và định nghĩa các method với các annotation tương ứng như @Before, @After, @Around, @AfterRunning, @AfterThrowing
           - @Before: Chạy trước khi phương thức được gọi.
           - @AfterReturning: Chạy sau khi phương thức thực thi thành công.
           - @AfterThrowing: Chạy sau khi phương thức ném ra một ngoại lệ.
           - @After: Chạy sau khi phương thức kết thúc, bất kể phương thức đó có thành công hay không.
           - @Around: Chạy trước và sau khi phương thức thực thi.
   - Mô hình mvc trong spring boot:
     - Model là các lớp entity, một entity sẽ tương ứng với một table dưới database, nó phải chứa đầy đủ các thông tin như tên bảng, khóa chính, khóa ngoại và các cột trong bảng.
     - View có thể là các JSP hay Thymeleaf hoặc là khi các ứng dụng Restful Api kết hợp với các công nghệ front-end như ReactJs, VueJs, ...
     - Controller xử lý các yêu cầu của người dùng, tương tác với model và trả về kết quả cho view.
   - Luồng đi của spring boot mvc:
     1. Người dùng vào view để xem và tương tác.
     2. View lúc này sẽ gửi 1 http request bao gồm request url, request method, request headers hoặc request body đến Controller.
     3. Controller lúc này nhận được yêu cầu và gửi sang cho Service (service trong spring là nơi xử lý các logic, sử dụng 1 component là @Service).
     4. Service nhận được yêu cầu từ Controller và bắt đầu xử lý logic, nếu các logic không liên quan đến csdl thì không cần đưa cho tầng repository mà trả về lại Controller luôn. Nếu đụng tới csdl thì sẽ gửi cho Repository để xử lý.
     5. Repository nhận được thông báo từ Service, thao tác với csdl. Dữ liệu trong csdl được hệ thống ORM map thành các Entity.
     6. Sau khi xử lý xong Repository gửi lại cho Service, sau đó Service sẽ tính toán logic theo yêu cầu và trả về cho tầng Controller.
     7. Controller nhận được kết quả từ Service sẽ trả về cho View. Kết quả trả về là các http response bao gồm response status code, response headers và response body.
        
   - Những lý do sử dụng spring boot:
     1. Phát triển nhanh chóng và dễ dàng các ứng dụng dựa trên Spring.
     2. Cho phép tích hợp trực tiếp các máy chủ như Tomcat, Jetty hoặc Undertow vào ứng dụng.
     3. Không cần cấu hình bằng XML.
     4. Khởi động dễ dàng.
     5. Dễ dàng cài đặt và quản lý.
     6. Sở hữu một cộng đồng lớn, giúp rút ngắn thời gian làm quen.
     7. Server Tomcat được nhúng ngay trong file JAR build ra, chỉ cần chạy ở bất kì đâu java chạy được
