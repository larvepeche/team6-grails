<div class="form-group">
    <label>Text Content</label>
    <g:textField name="name" class="form-control" value="${product?.name}" placeholder="Name"/>
</div>

<div class="form-group">
    <label>Text Content</label>
    <g:field type="number" name="price" class="form-control" value="${product?.price}" placeholder="Price"/>
</div>

<div class="form-group">
    <g:field name="productImage" class="form-control" type="file" placeholder="Please Upload Image"/>
    <g:if test="${product?.image}">
        <img src="${resource(dir: "banner-image", file: "/${product.id}-${product.image}")}" class="img-thumbnail" style="margin-top: 10px; height: 100px; width: 100px;"/>
    </g:if>
</div>