package inventory.service.impl;

import inventory.api.v1.model.*;
import inventory.entity.Asset;
import inventory.entity.InventoryMaster;
import inventory.repository.InventoryMasterRepository;
import inventory.service.*;
import inventory.utils.DateUtils;
import inventory.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("inventoryMasterService")
public class InventoryMasterServiceImpl implements InventoryMasterService {

    @Autowired
    private InventoryMasterRepository inventoryMasterRepository;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private InventoryDetailService inventoryDetailService;
    @Autowired
    private LogService logService;


    @Override
    public OcreateMaster createMaster(IcreateMaster input, String userName) {

        if(input.getDeptId() == null || input.getDeptId() <=0)
            return OcreateMaster.returnError("部門ID為空(DeptId is null)!!");
        if(input.getFromTime() == null)
            return OcreateMaster.returnError("開始時間不能為空(fromTime is null)!!");
        if(DateUtils.toDate(input.getFromTime(),"yyyy/MM/dd") == null)
            return OcreateMaster.returnError("開始時間格式錯誤(fromTime format error)!!");
        if(input.getEndTime() == null)
            return OcreateMaster.returnError("結束時間不能為空(fromTime is null)!!");
        if(DateUtils.toDate(input.getEndTime(),"yyyy/MM/dd") == null)
            return OcreateMaster.returnError("結束時間格式錯誤(fromTime format error)!!");
        if(StringUtils.isEmpty(input.getTitle()))
            return OcreateMaster.returnError("標題不能為空(title is null)!!");




        InventoryMaster inventoryMaster = new InventoryMaster();
        inventoryMaster.setTitle(input.getTitle());
        inventoryMaster.setRemark(input.getRemark());
        inventoryMaster.setDeptId(input.getDeptId());
        inventoryMaster.setFromTime(DateUtils.toDate(input.getFromTime(),"yyyy/MM/dd"));
        inventoryMaster.setEndTime(DateUtils.toDate(input.getEndTime(),"yyyy/MM/dd"));
        inventoryMaster.setCreateTime(new Date());
        inventoryMaster.setModifiedTime(new Date());
        if(!StringUtils.isEmpty(input.getUserName())){
            inventoryMaster.setModifiedByUser(input.getUserName());
        }else{
            inventoryMaster.setModifiedByUser(accessTokenService.getUserName(input.getAccessToken()));
        }

        inventoryMaster.setStatus(InventoryMaster.STATUS.ACTIVE);

        InventoryMaster saved = inventoryMasterRepository.save(inventoryMaster);
        if(saved==null || saved.getId() == null || saved.getId()<=0)
            return OcreateMaster.returnError("保存失敗!!");

        List<Asset> assets = assetService.findByDeptId(input.getDeptId());
        for(Asset asset: assets){
            inventoryDetailService.save(saved.getId(), asset, userName);
        }

        OcreateMaster output = OcreateMaster.returnSuccess(saved,"");
        logService.save(saved.getId(), saved.getTitle(), "createMaster",  input, output, userName);
        return output;
    }

    @Override
    public OsearchMaster searchMaster(IsearchMaster input) {
        if( (input.getDeptId() == null || input.getDeptId() <=0 ) && StringUtils.isEmpty(input.getTitle()))
            return OsearchMaster.returnError("没有查詢條件!!");

        List<InventoryMaster> list = null;
        if( (input.getDeptId() != null && input.getDeptId() > 0 ) && StringUtils.isEmpty(input.getTitle()) ){
            list = inventoryMasterRepository.findByDeptId(input.getDeptId());
        } else if( (input.getDeptId() == null || input.getDeptId() <= 0 ) && !StringUtils.isEmpty(input.getTitle()) ){
            list = inventoryMasterRepository.findByTitle(input.getTitle());
        } else if( (input.getDeptId() != null && input.getDeptId() > 0 ) && !StringUtils.isEmpty(input.getTitle()) ){
            list = inventoryMasterRepository.findByDeptIdAndTitle(input.getDeptId(),input.getTitle());
        }

        return OsearchMaster.returnSuccess(list,"");
    }

    @Override
    public OsearchMasterById searchMasterId(IsearchMasterById input) {
        if( input.getId() == null || input.getId() <=0 )
            return OsearchMasterById.returnError("没有查詢條件!!");

        InventoryMaster inventoryMaster = null;
        Optional<InventoryMaster> optional = inventoryMasterRepository.findById(input.getId());
        if (optional.isPresent()) {
            inventoryMaster = optional.get();
        } else {
            return OsearchMasterById.returnSuccess(null, "没有資料");
        }
        return OsearchMasterById.returnSuccess(inventoryMaster,"");

    }

    @Override
    public OsearchMaster searchMasterByHolder(IsearchMasterByHolder input) {
        String holderName = null;
        if( !StringUtils.isEmpty(input.getHolderName()) )
            holderName = input.getHolderName();
        else
            holderName = this.accessTokenService.getUserName(input.getAccessToken());

        List<InventoryMaster> result = new ArrayList<InventoryMaster>();
        List<InventoryMaster> list = inventoryMasterRepository.searchMasterByInventoryHolder(input.getHolderName());
        Map<Integer,Boolean> filterMap = new HashMap<Integer,Boolean>();
        for(InventoryMaster node:list){
            Boolean isExist = filterMap.get(node.getId());
            if(isExist==null || !isExist){
                filterMap.put(node.getId(), true);
                result.add(node);
            }
        }

        return OsearchMaster.returnSuccess(result,"");

    }


    @Override
    public OeditMaster editMaster(IeditMaster input, String userName) {
        if(input.getId() == null || input.getId() <=0)
            return OeditMaster.returnError("ID為空(Id is null)!!");
        if(input.getDeptId() == null || input.getDeptId() <=0)
            return OeditMaster.returnError("部門ID為空(DeptId is null)!!");
        if(input.getFromTime() == null)
            return OeditMaster.returnError("開始時間不能為空(fromTime is null)!!");
        if(DateUtils.toDate(input.getFromTime(),"yyyy/MM/dd") == null)
            return OeditMaster.returnError("開始時間格式錯誤(fromTime format error)!!");
        if(input.getEndTime() == null)
            return OeditMaster.returnError("結束時間不能為空(fromTime is null)!!");
        if(DateUtils.toDate(input.getEndTime(),"yyyy/MM/dd") == null)
            return OeditMaster.returnError("結束時間格式錯誤(fromTime format error)!!");
        if(StringUtils.isEmpty(input.getTitle()))
            return OeditMaster.returnError("標題不能為空(title is null)!!");
        if(StringUtils.isEmpty(input.getStatus()))
            return OeditMaster.returnError("没有狀態1(status is null)!!");
        if(InventoryMaster.STATUS.toStatus(input.getStatus()) == null)
            return OeditMaster.returnError("没有狀態2(status is null)!!");

        InventoryMaster inventoryMaster=null;
        Optional<InventoryMaster> optional = inventoryMasterRepository.findById(input.getId());
        if (optional.isPresent()) {
            inventoryMaster = optional.get();
        } else {
            return OeditMaster.returnSuccess(null, "没有資料");
        }

        inventoryMaster.setTitle(input.getTitle());
        inventoryMaster.setRemark(input.getRemark());
        inventoryMaster.setDeptId(input.getDeptId());
        inventoryMaster.setFromTime(DateUtils.toDate(input.getFromTime(),"yyyy/MM/dd"));
        inventoryMaster.setEndTime(DateUtils.toDate(input.getEndTime(),"yyyy/MM/dd"));
        inventoryMaster.setModifiedTime(new Date());
        if(!StringUtils.isEmpty(input.getUserName())){
            inventoryMaster.setModifiedByUser(input.getUserName());
        }else{
            inventoryMaster.setModifiedByUser(accessTokenService.getUserName(input.getAccessToken()));
        }
        inventoryMaster.setStatus(InventoryMaster.STATUS.toStatus(input.getStatus()));

        InventoryMaster saved = inventoryMasterRepository.save(inventoryMaster);
        if(saved==null || saved.getId() == null || saved.getId()<=0)
            return OeditMaster.returnError("保存失敗!!");

        OeditMaster output = OeditMaster.returnSuccess(saved,"");
        logService.save(saved.getId(), saved.getTitle(), "editMaster",  input, output,userName);
        return output;
    }

    @Override
    public OdeleteMaster deleteMaster(IdeleteMaster input, String userName) {

        if(input.getId() == null || input.getId() <=0)
            return OdeleteMaster.returnError("ID為空(Id is null)!!");

        InventoryMaster inventoryMaster=null;
        Optional<InventoryMaster> optional = inventoryMasterRepository.findById(input.getId());
        if (optional.isPresent()) {
            inventoryMaster = optional.get();
        } else {
            return OdeleteMaster.returnSuccess("刪除成功");
        }

        inventoryMasterRepository.delete(inventoryMaster);
        OdeleteMaster output =  OdeleteMaster.returnSuccess("刪除成功");

        logService.save(input.getId(), null, "deleteMaster",  input, output,userName);
        return output;


    }
}
