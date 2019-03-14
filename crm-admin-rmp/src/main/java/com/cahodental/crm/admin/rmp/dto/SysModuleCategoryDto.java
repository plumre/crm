package com.cahodental.crm.admin.rmp.dto;


import com.cahodental.crm.admin.rmp.model.SysModuleCategory;

import java.util.List;

public class SysModuleCategoryDto extends SysModuleCategory {
    private List<SysModuleDto> sysModuleDtoList;

    public List<SysModuleDto> getSysModuleDtoList() {
        return sysModuleDtoList;
    }

    public void setSysModuleDtoList(List<SysModuleDto> sysModuleDtoList) {
        this.sysModuleDtoList = sysModuleDtoList;
    }
}
