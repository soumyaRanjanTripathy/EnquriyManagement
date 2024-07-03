package com.demo.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter //DashboardData 
@Getter
public class DashboardResponse {
    private Integer totalEnquriesCnt;
    private Integer enrolledCnt;
    private Integer lostCnt;}