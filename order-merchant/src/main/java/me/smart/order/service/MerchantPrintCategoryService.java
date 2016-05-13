package me.smart.order.service;

import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MerchantCourseCategoryAddRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryListRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryUpdateRequest;

/**
 * Created by zhangxiong on 16/4/18.
 */
public interface MerchantPrintCategoryService {

    Result addCategory(MerchantCourseCategoryAddRequest request) throws Exception;

    Result deleteCategory() throws Exception;

    Result updateCategory(MerchantCourseCategoryUpdateRequest request) throws Exception;

    Result listCategory(MerchantCourseCategoryListRequest request) throws Exception;


}
