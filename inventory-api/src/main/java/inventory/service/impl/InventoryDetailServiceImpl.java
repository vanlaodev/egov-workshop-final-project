package inventory.service.impl;

import inventory.api.v1.model.*;
import inventory.entity.Asset;
import inventory.entity.InventoryDetail;
import inventory.repository.InventoryDetailRepository;
import inventory.service.InventoryDetailService;
import inventory.service.LogService;
import inventory.utils.DateUtils;
import inventory.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("inventoryDetailService")
public class InventoryDetailServiceImpl implements InventoryDetailService {

    @Autowired
    private InventoryDetailRepository inventoryDetailRepository;
    @Autowired
    private LogService logService;

    @Override
    public InventoryDetail save(Integer inventoryDetailId, Asset asset, String userName) {
        InventoryDetail inventoryDetail = new InventoryDetail();
        inventoryDetail.setInventoryMasterId(inventoryDetailId);
        inventoryDetail.setAssetId(asset.getId());
        inventoryDetail.setAssetNum(asset.getAssetNum());
        inventoryDetail.setAssetType(asset.getAssetType());
        inventoryDetail.setBrand(asset.getBrand());
        inventoryDetail.setBudgetYear(asset.getBudgetYear());
        inventoryDetail.setDescription(asset.getDescription());
        inventoryDetail.setDimension(asset.getDimension());
        inventoryDetail.setEconomicClass(asset.getEconomicClass());
        inventoryDetail.setDept(asset.getDeptId());
        inventoryDetail.setFeature(asset.getFeature());
        inventoryDetail.setLocation(asset.getLocation());
        inventoryDetail.setModelNum(asset.getModelNum());
        inventoryDetail.setRemarkCn(asset.getRemarkCn());
        inventoryDetail.setRemarkPt(asset.getRemarkPt());
        inventoryDetail.setRetrievalMethod(asset.getRetrievalMethod());
        inventoryDetail.setSerialNum(asset.getSerialNum());
        inventoryDetail.setSupplier(asset.getSupplier());
        inventoryDetail.setUserName(asset.getUserName());
        inventoryDetail.setValue(asset.getValue());
        inventoryDetail.setYear(asset.getYear());

        InventoryDetail output = inventoryDetailRepository.save(inventoryDetail);
        logService.save(inventoryDetail.getInventoryMasterId(), null, "InventoryDetail save",  null, output, userName);
        return output;
    }

    @Override
    public OeditDetail editDetail(IeditDetail input, String userName) {

        InventoryDetail inventoryDetail=null;
        Optional<InventoryDetail> optional = inventoryDetailRepository.findById(input.getId());
        if (optional.isPresent()) {
            inventoryDetail = optional.get();
        } else {
            return OeditDetail.returnSuccess(null, "没有資料");
        }

        inventoryDetail.setActionBy(input.getActionBy());
        inventoryDetail.setInventoryMasterId(input.getInventoryMasterId());
        inventoryDetail.setDept(input.getDept());
        inventoryDetail.setAssetId(input.getAssetId());
        inventoryDetail.setBrand(input.getBrand());
        inventoryDetail.setDescription(input.getDescription());
        inventoryDetail.setActionResult(input.getActionResult());
        inventoryDetail.setActionTime(DateUtils.toDate(input.getActionTime(),"yyyy/MM/dd"));
        inventoryDetail.setAssetNum(input.getAssetNum());
        inventoryDetail.setAssetType(input.getAssetType());
        inventoryDetail.setBudgetYear(input.getBudgetYear());
        inventoryDetail.setDimension(input.getDimension());
        inventoryDetail.setEconomicClass(input.getEconomicClass());
        inventoryDetail.setFeature(input.getFeature());
        inventoryDetail.setLocation(input.getLocation());
        inventoryDetail.setModelNum(input.getModelNum());
        inventoryDetail.setRemarkCn(input.getRemarkCn());
        inventoryDetail.setRemarkPt(input.getRemarkPt());
        inventoryDetail.setRetrievalMethod(input.getRetrievalMethod());
        inventoryDetail.setSerialNum(input.getSerialNum());
        inventoryDetail.setSupplier(input.getSupplier());
        inventoryDetail.setUserName(input.getUserName());
        inventoryDetail.setValue(input.getValue());


        InventoryDetail saved = inventoryDetailRepository.save(inventoryDetail);
        if(saved==null || saved.getId() == null || saved.getId()<=0)
            return OeditDetail.returnError("保存失敗!!");

        OeditDetail output = OeditDetail.returnSuccess(saved,"");
        logService.save(inventoryDetail.getInventoryMasterId(), null, "editDetail",  input, output, userName);
        return output;
    }

    @Override
    public OdeleteDetail deleteDetail(IdeleteDetail input, String userName) {

        if(input.getId() == null || input.getId() <=0)
            return OdeleteDetail.returnError("ID為空(Id is null)!!");

        InventoryDetail inventoryDetail=null;
        Optional<InventoryDetail> optional = inventoryDetailRepository.findById(input.getId());
        if (optional.isPresent()) {
            inventoryDetail = optional.get();
        } else {
            return OdeleteDetail.returnSuccess("刪除成功");
        }

        inventoryDetailRepository.delete(inventoryDetail);
        OdeleteDetail output = OdeleteDetail.returnSuccess("刪除成功");
        logService.save(input.getId(), null, "deleteDetail",  input, output, userName);
        return output;
    }

    @Override
    public OsearchDetail searchDetail(IsearchDetail input) {
        if( (input.getMasterId() == null || input.getMasterId() <=0 ) && StringUtils.isEmpty(input.getUserName()))
            return OsearchDetail.returnError("没有查詢條件!!");

        List<InventoryDetail> list = null;
        if( (input.getMasterId() != null && input.getMasterId() > 0 ) && StringUtils.isEmpty(input.getUserName()) ){
            list = inventoryDetailRepository.findByInventoryMasterId(input.getMasterId());
        } else if( (input.getMasterId() == null || input.getMasterId() <= 0 ) && !StringUtils.isEmpty(input.getUserName()) ){
            list = inventoryDetailRepository.findByUserName(input.getUserName());
        } else if( (input.getMasterId() != null && input.getMasterId() > 0 ) && !StringUtils.isEmpty(input.getUserName()) ){
            list = inventoryDetailRepository.findByInventoryMasterIdAndUserName(input.getMasterId(),input.getUserName());
        }

        return OsearchDetail.returnSuccess(list,"");
    }
}
