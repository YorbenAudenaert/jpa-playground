# jpa-playground

This simple project is designed to demonstrate the powers of `spring-boot-data-jpa` and `hibernate`.
The domain design is kept simple to make the use cases simple and understandeable while still allowing
more complex transactions.

I've applied a hexagonal architecture in this example to demonstrate that the hibernate persistent cache is applicable
for the entire transaction. Even if `@Transactional` is used in the service layer.

NOTE: Most integration tests do not contain any assertions. Simple due to the fact that this project was designed
to test the behaviour of `spring-boot-data-jpa` and `hibernate`. We use the `spring.jpa.show-sql=true` property's output
to validate if the behaviour is as expected.

### Case 1: updateProductPrice()

This usecase simply updates the price of an existing product. When executing the integration test we see the following
result:

```
Hibernate: select p1_0."id",c1_0."id",c1_0."description",p1_0."description",p1_0."price" from "product_entity" p1_0 left join "category_entity" c1_0 on c1_0."id"=p1_0."fk_category" where p1_0."id"=?
Hibernate: select p1_0."product_id",p1_0."id",p1_0."description",p1_0."value" from "product_attribute_entity" p1_0 where p1_0."product_id"=?
Hibernate: update "product_entity" set "fk_category"=?,"description"=?,"price"=? where "id"=?
Hibernate: update "product_attribute_entity" set "description"=?,"product_id"=?,"value"=? where "id"=?
```

We see that, even though our product attributes are marked as `FetchType.LAZY`, the attributes are fetched as well.
This is cause by the ObjectMappers accessing getProductAttributes() of the retrieved product. This is a common pitfall.

We also see that no other SELECT statement is executed after the UPDATE. Even if my service layer would call the
jpa-adapter again with `findById()`. The hibernate persistent cache knows that the most recent version of Product is
stored in its cache and will simply return that instance instead of retrieving it again from the datasource.

### Case 2: updateProductAttributeValue()

Next usecase will attempt to update the ProductAttribute's value of an existing product. The code will unexpectedly also
update the description of the attribute to test the intelligence of the hibernate persistent cache.
When executing the integration test we see the following result:

```
Hibernate: select p1_0."id",c1_0."id",c1_0."description",p1_0."description",p1_0."price" from "product_entity" p1_0 left join "category_entity" c1_0 on c1_0."id"=p1_0."fk_category" where p1_0."id"=?
Hibernate: select p1_0."product_id",p1_0."id",p1_0."description",p1_0."value" from "product_attribute_entity" p1_0 where p1_0."product_id"=?
2023-10-16T16:14:07.529+02:00  INFO 4626 --- [           main] c.a.j.p.service.UpdateProductService     : Saving product with new value...
2023-10-16T16:14:07.535+02:00  INFO 4626 --- [           main] c.a.j.p.service.UpdateProductService     : Unexpectedly change the attribute description!!!
Hibernate: update "product_attribute_entity" set "description"=?,"product_id"=?,"value"=? where "id"=?
```

In this case we call the productJpaRepository.save() two different times. Even though we only see a single INSERT in the
logs. The hibernate persistent cache will only persist the changes when a commit occurs. Hibernate manages when this
commit will occur based on the state of the current transaction.

After the update is performed we call the `getProductById()` method 4 more times. However this is not visible in the
hibernate logging. The updated version of the productEntity is still in the persistent cache.
