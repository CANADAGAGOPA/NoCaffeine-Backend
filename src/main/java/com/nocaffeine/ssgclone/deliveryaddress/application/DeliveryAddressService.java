package com.nocaffeine.ssgclone.deliveryaddress.application;

import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressModifyRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressDetailResponseDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressListResponseDto;

import java.util.List;

public interface DeliveryAddressService {

    void addDeliveryAddress(DeliveryAddressAddRequestDto deliveryAddressAddRequestDto, String memberUuid);
    void removeDeliveryAddress(Long addressId , String memberUuid);
    void modifyDeliveryAddress(DeliveryAddressModifyRequestDto deliveryAddressModifyRequestDto, String memberUuid);
    List<DeliveryAddressListResponseDto> findDeliveryAddress(String memberUuid);
    void setDefaultDeliveryAddress(Long deliveryAddressId, String memberUuid);
    DeliveryAddressDetailResponseDto findDeliveryAddressDetail(Long addressId, String memberUuid);


}
