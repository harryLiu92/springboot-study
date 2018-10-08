package com.home.web.service;

import com.home.web.dao.MerchantDAO;
import com.home.web.dto.BaiduApiBatchRoadResponse;
import com.home.web.dto.MerchantDTO;
import com.home.web.logger.BaseService;
import com.home.web.model.Merchant;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Administrator liuhao
 * 2018/9/21 22:21
 **/
@Service
public class MerchantService extends BaseService {

    @Autowired
    private MerchantDAO merchantDAO;

    public List<Merchant> queryNearestByLongitudeAndLatitude(Double latitude, Double longitude) {
        return merchantDAO.queryNearestByLongitudeAndLatitude(latitude, longitude);
    }

    public List<MerchantDTO> buildDTO(List<Merchant> merchantList, BaiduApiBatchRoadResponse response) {
        List<MerchantDTO> merchantDTOS = Lists.newArrayList();
        for (int i=0; i<merchantList.size(); i++) {
            MerchantDTO dto = new MerchantDTO();

            Merchant merchant = merchantList.get(i);

            BaiduApiBatchRoadResponse.BatchRoadResult result = response.getResult().get(i);

            BeanUtils.copyProperties(merchant, dto);

            dto.setDistance(result.getDistance().getText());
            dto.setDuration(result.getDuration().getText());

            merchantDTOS.add(dto);
        }

        return merchantDTOS;
    }
}