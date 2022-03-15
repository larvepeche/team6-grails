<div class="form-group">
    <label>Text Content</label>
    <g:textField name="textContent" class="form-control" value="${banner?.textContent}" placeholder="Enter Banner content"/>
</div>

<div class="form-group">
    <g:field name="bannerImage" class="form-control" type="file" placeholder="Please Upload Image"/>
    <g:if test="${banner?.image}">
        <img src="${resource(dir: "banner-image", file: "/${banner.id}-${banner.image}")}" class="img-thumbnail" style="margin-top: 10px; height: 100px; width: 100px;"/>
    </g:if>
</div>