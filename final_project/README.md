# EVAM Java Bootcamp Bitirme Projesi

* Bir müşteri bilgisi alıp kayıt eden, bir fatura bilgisi kayıt eden ve bu bilgileri sorgulayan restApi ler olacak.
* Bir de ödenmiş statüsünde gözüken fatura kaydı oluşturalım. Müşterinin faturası sorgulandığında ödenmemiş faturanın bulunmadığına dair response code ve mesaj dönülsün. (Fatura sorgulama faturaId ve müşteri numarası ile yapılmalı)
* Oluşturulan müşteri kaydı ve fatura kaydı için id bilgisi ile silme işlemleri yapan 2 servis olsun.
* Fatura kaydı oluşturulacak, kayıt sorgulanabilecek.
* Müşteri bilgisi update eden bir servis olacak.
* Bu işlemlerin postgreSql e giden sorgular ile yapacağız. Respository bağlantısı olmalı.
* Proje bir maven projesi olacak. Springboot framework ü ile ve SOLID prensiplerine uygun şekilde yazılacak.

3 adet tablo yeterli. Fatura, User, Payment
Payment işlemini doğrudan yapılmış gibi hazır kayıt oluşturulması
yeterli.
Servisler ResponseEntity tipinde cevap dönmeli.

Readme dosyasında bu restApi ler için açıklamalı Postman den nasıl
istek atıldığını gösteren örnek Request ve response lar içeren,
GET/PUT/POST/DELETE işlemlerini açıklayan bir açıklama bulunsun.

Aşağıdaki pathler örnektir, gerektiği kadar servis
oluşturabilirsiniz.


1. https://localhost:8080/v1/payments/getAllDebts-> list
of debts
2. https://localhost:8080/v1/debts -> create debt -> (the
return type must be Response.class and it should
contains code, date, explanation) and the return code
must be 0, explanation = "The debt is created
succesfully"
3. https://localhost:8080/v1/payments/{debtId} -> update
the debt statu (if it is paid returns 1 and a
explanation like "The debt is allready paid" (the
return type must be Response.class and it should
contains code, date, explanation)
4. https://localhost:8080/v1/payments/{debtId} -> delete
the debt record from table.
5. https://localhost:8080/v1/users/createUser
6. https://localhost:8080/v1/users/queryUserInfo