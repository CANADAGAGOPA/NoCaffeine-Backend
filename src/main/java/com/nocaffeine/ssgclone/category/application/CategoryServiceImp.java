package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import com.nocaffeine.ssgclone.category.domain.*;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.category.infrastructure.*;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.Total;
import com.nocaffeine.ssgclone.product.infrastructure.ProductImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.product.infrastructure.TotalRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_PRODUCT;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.No_TINY_CATEGORY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService {

    private final LargeCategoryRepository largeCategoryRepository;

    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;


    @Override
    public List<LargeCategoryResponse> findLargeCategories() {
        List<LargeCategoryResponse> largeCategoryList = new ArrayList<>();

        for (LargeCategory largeCategory : largeCategoryRepository.findAll()){
            LargeCategoryResponse largeCategoryDto = LargeCategoryResponse.builder()
                    .large_category_id(largeCategory.getId())
                    .large_category_name(largeCategory.getName())
                    .build();
            largeCategoryList.add(largeCategoryDto);
        }
        return largeCategoryList;
    }

    @Override
    public List<MediumCategoryResponse> findMediumCategories(Long largeId) {

        List<MediumCategoryResponse> mediumCategoryDtoList = new ArrayList<>();

        for (MediumCategory mediumCategory : mediumCategoryRepository.findByLargeCategoryId(largeId)) {
            MediumCategoryResponse mediumCategoryDto = MediumCategoryResponse.builder()
                    .medium_category_id(mediumCategory.getId())
                    .medium_category_name(mediumCategory.getName())
                    .build();
            mediumCategoryDtoList.add(mediumCategoryDto);
        }
        return mediumCategoryDtoList;
    }

    @Override
    public List<SmallCategoryResponse> findSmallCategories(Long mediumId) {
        List<SmallCategoryResponse> smallCategoryDtoList = new ArrayList<>();

        for (SmallCategory smallCategory : smallCategoryRepository.findByMediumCategoryId(mediumId)) {
            SmallCategoryResponse smallCategoryDto = SmallCategoryResponse.builder()
                    .small_category_id(smallCategory.getId())
                    .small_category_name(smallCategory.getName())
                    .build();
            smallCategoryDtoList.add(smallCategoryDto);

        }

        return smallCategoryDtoList;
    }

    @Override
    public List<TinyCategoryResponse> findSmalltoTiny(Long smallId) {
        List<TinyCategoryResponse> tinyCategoryDtoList = new ArrayList<>();

        for (TinyCategory tinyCategory : tinyCategoryRepository.findBySmallCategoryId(smallId)) {
            TinyCategoryResponse tinyCategoryDto = TinyCategoryResponse.builder()
                    .tiny_category_id(tinyCategory.getId())
                    .tiny_category_name(tinyCategory.getName())
                    .build();
            tinyCategoryDtoList.add(tinyCategoryDto);
        }
        if (tinyCategoryDtoList.isEmpty()) {
            throw new BaseException(No_TINY_CATEGORY);
        }

        return tinyCategoryDtoList;
    }
}








