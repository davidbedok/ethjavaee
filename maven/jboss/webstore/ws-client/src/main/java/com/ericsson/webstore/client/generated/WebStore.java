
package com.ericsson.webstore.client.generated;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebStore", targetNamespace = "http://www.ericsson.com/WebStore")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebStore {


    /**
     * 
     * @return
     *     returns com.ericsson.webstore.client.generated.Basket
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "GetBasketContent", action = "http://www.ericsson.com/WebStore/getBasketContent")
    @WebResult(name = "Basket", targetNamespace = "http://www.ericsson.com/WebStore")
    @RequestWrapper(localName = "GetBasketContent", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketContent")
    @ResponseWrapper(localName = "GetBasketContentResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketContentResponse")
    public Basket getBasketContent()
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param productName
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "RemoveItemFromBasket", action = "http://www.ericsson.com/WebStore/removeItemFromBasket")
    @RequestWrapper(localName = "RemoveItemFromBasket", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.RemoveItemFromBasket")
    @ResponseWrapper(localName = "RemoveItemFromBasketResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.RemoveItemFromBasketResponse")
    public void removeItemFromBasket(
        @WebParam(name = "ProductName", targetNamespace = "")
        String productName)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param name
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "DeleteProduct", action = "http://www.ericsson.com/WebStore/deleteProduct")
    @RequestWrapper(localName = "DeleteProduct", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.DeleteProduct")
    @ResponseWrapper(localName = "DeleteProductResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.DeleteProductResponse")
    public void deleteProduct(
        @WebParam(name = "Name", targetNamespace = "")
        String name)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "GetBasketIdentifier", action = "http://www.ericsson.com/WebStore/getBasketIdentifier")
    @WebResult(name = "Identifier", targetNamespace = "")
    @RequestWrapper(localName = "GetBasketIdentifier", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketIdentifier")
    @ResponseWrapper(localName = "GetBasketIdentifierResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketIdentifierResponse")
    public String getBasketIdentifier()
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param name
     * @return
     *     returns com.ericsson.webstore.client.generated.ProductStub
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "GetProduct", action = "http://www.ericsson.com/WebStore/getProduct")
    @WebResult(name = "Product", targetNamespace = "http://www.ericsson.com/WebStore")
    @RequestWrapper(localName = "GetProduct", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetProduct")
    @ResponseWrapper(localName = "GetProductResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetProductResponse")
    public ProductStub getProduct(
        @WebParam(name = "Name", targetNamespace = "")
        String name)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param product
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "AddProduct", action = "http://www.ericsson.com/WebStore/addProduct")
    @RequestWrapper(localName = "AddProduct", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.AddProduct")
    @ResponseWrapper(localName = "AddProductResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.AddProductResponse")
    public void addProduct(
        @WebParam(name = "Product", targetNamespace = "http://www.ericsson.com/WebStore")
        ProductStub product)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param nameTerm
     * @return
     *     returns java.util.List<com.ericsson.webstore.client.generated.ProductStub>
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "ListProductsByNameTerm", action = "http://www.ericsson.com/WebStore/listProductsByNameTerm")
    @WebResult(name = "Product", targetNamespace = "http://www.ericsson.com/WebStore")
    @RequestWrapper(localName = "ListProductsByNameTerm", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListProductsByNameTerm")
    @ResponseWrapper(localName = "ListProductsByNameTermResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListProductsByNameTermResponse")
    public List<ProductStub> listProductsByNameTerm(
        @WebParam(name = "NameTerm", targetNamespace = "")
        String nameTerm)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param brand
     * @return
     *     returns java.util.List<com.ericsson.webstore.client.generated.ProductStub>
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "ListProductsByBrand", action = "http://www.ericsson.com/WebStore/listProductsByBrand")
    @WebResult(name = "Product", targetNamespace = "http://www.ericsson.com/WebStore")
    @RequestWrapper(localName = "ListProductsByBrand", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListProductsByBrand")
    @ResponseWrapper(localName = "ListProductsByBrandResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListProductsByBrandResponse")
    public List<ProductStub> listProductsByBrand(
        @WebParam(name = "Brand", targetNamespace = "")
        BrandStub brand)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @return
     *     returns java.util.List<com.ericsson.webstore.client.generated.ProductStub>
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "ListAllProducts", action = "http://www.ericsson.com/WebStore/listAllProducts")
    @WebResult(name = "Product", targetNamespace = "http://www.ericsson.com/WebStore")
    @RequestWrapper(localName = "ListAllProducts", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListAllProducts")
    @ResponseWrapper(localName = "ListAllProductsResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.ListAllProductsResponse")
    public List<ProductStub> listAllProducts()
        throws WebStoreServiceException
    ;

    /**
     * 
     * @return
     *     returns int
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "GetBasketSize", action = "http://www.ericsson.com/WebStore/getBasketSize")
    @WebResult(name = "BasketSize", targetNamespace = "")
    @RequestWrapper(localName = "GetBasketSize", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketSize")
    @ResponseWrapper(localName = "GetBasketSizeResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.GetBasketSizeResponse")
    public int getBasketSize()
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param identifier
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "SetBasketIdentifier", action = "http://www.ericsson.com/WebStore/setBasketIdentifier")
    @RequestWrapper(localName = "SetBasketIdentifier", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.SetBasketIdentifier")
    @ResponseWrapper(localName = "SetBasketIdentifierResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.SetBasketIdentifierResponse")
    public void setBasketIdentifier(
        @WebParam(name = "Identifier", targetNamespace = "")
        String identifier)
        throws WebStoreServiceException
    ;

    /**
     * 
     * @param productName
     * @throws WebStoreServiceException
     */
    @WebMethod(operationName = "AddItemToBasket", action = "http://www.ericsson.com/WebStore/addItemToBasket")
    @RequestWrapper(localName = "AddItemToBasket", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.AddItemToBasket")
    @ResponseWrapper(localName = "AddItemToBasketResponse", targetNamespace = "http://www.ericsson.com/WebStore", className = "com.ericsson.webstore.client.generated.AddItemToBasketResponse")
    public void addItemToBasket(
        @WebParam(name = "ProductName", targetNamespace = "")
        String productName)
        throws WebStoreServiceException
    ;

}
