# Evam Java Bootcamp Final Project

* Bir müşteri bilgisi alıp kayıt eden, bir fatura bilgisi kayıt eden ve bu bilgileri sorgulayan restApi ler olacak.
* Bir de ödenmiş statüsünde gözüken fatura kaydı oluşturalım. Müşterinin faturası sorgulandığında ödenmemiş faturanın bulunmadığına dair response code ve mesaj dönülsün. (Fatura sorgulama faturaId ve müşteri numarası ile yapılmalı)
* Oluşturulan müşteri kaydı ve fatura kaydı için id bilgisi ile silme işlemleri yapan 2 servis olsun.
* Fatura kaydı oluşturulacak, kayıt sorgulanabilecek.
* Müşteri bilgisi update eden bir servis olacak.
* Bu işlemlerin postgreSql e giden sorgular ile yapacağız. Respository bağlantısı olmalı.
* Proje bir maven projesi olacak. Springboot framework ü ile ve SOLID prensiplerine uygun şekilde yazılacak.

3 adet tablo yeterli. Fatura, User, Payment Payment işlemini doğrudan yapılmış gibi hazır kayıt oluşturulması yeterli. Servisler ResponseEntity tipinde cevap dönmeli.

Readme dosyasında bu restApi ler için açıklamalı Postman den nasıl istek atıldığını gösteren örnek Request ve response lar içeren, GET/PUT/POST/DELETE işlemlerini açıklayan bir açıklama bulunsun.

## Kullanılan Servisler

* http://localhost:8080/final/client/get -> tüm client bilgilerini sıralar
* http://localhost:8080/final/invoice/get -> tüm invoice bilgilerini sıralar
* http://localhost:8080/final/payment/get -> tüm payment bilgilerini sıralar
* http://localhost:8080/final/client/get{ID} -> belirtilen id değerine sahip client bilgisini getirir
* http://localhost:8080/final/invoice/get/{ID} -> belirtilen id değerine sahip fatura bilgisini getirir
* http://localhost:8080/final/payment/get/{ID} -> belirtilen id değerine sahip payment bilgisini getirir

* http://localhost:8080/final/client/save -> yeni bir client eklemek için kullanılabilir. Parametre olarak JSON tipinde veri kabul eder. Client sınıfı için: first_name, last_name bilgileri isteğe bağlı olarak verilebilir.
* http://localhost:8080/final/invoice/save -> yeni bir invoice eklemek için kullanılabilir. Yine JSON tipinde parametre ister ve client_id, debt ve date alanları isteğe bağlı olarak verilebilir
* http://localhost:8080/final/payment/save -> yeni bir payment oluşturmak için kullanılır

* http://localhost:8080/final/client/delete/{ID} -> {ID} içerisine yazılan id değeri ile bu ad adresine sahip müşteri silinir.
* http://localhost:8080/final/invoice/delete/{ID}

* http://localhost:8080/final/client/update/{ID} -> belirtilen id değerine sahip client in bilgilerini gunceller, guncellenecek veriler parametre olarak verilmelidir
* http://localhost:8080/final/invoice/update/{ID} -> belirtilen id değerine sahip faturanın bilgilerini gunceller, guncellenecek veriler parametre olarak verilmelidir

* http://localhost:8080/final/invoice/status -> parametre olarak JSON tipinde verilen client bilgilerine göre belirtilen müşterinin kaç adet ödenmemiş faturası olduğunu belirtir.