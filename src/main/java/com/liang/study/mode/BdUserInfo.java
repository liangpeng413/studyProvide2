package com.liang.study.mode;

import java.math.BigDecimal;
import java.util.Date;

public class BdUserInfo {
    private Long id;

    private String sourceMemberId;

    private String memberChannelType;

    private String level;

    private BigDecimal countAum;

    private BigDecimal totalMoney;

    private String isAllocation;

    private Date firstInvestmentTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceMemberId() {
        return sourceMemberId;
    }

    public void setSourceMemberId(String sourceMemberId) {
        this.sourceMemberId = sourceMemberId == null ? null : sourceMemberId.trim();
    }

    public String getMemberChannelType() {
        return memberChannelType;
    }

    public void setMemberChannelType(String memberChannelType) {
        this.memberChannelType = memberChannelType == null ? null : memberChannelType.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public BigDecimal getCountAum() {
        return countAum;
    }

    public void setCountAum(BigDecimal countAum) {
        this.countAum = countAum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getIsAllocation() {
        return isAllocation;
    }

    public void setIsAllocation(String isAllocation) {
        this.isAllocation = isAllocation == null ? null : isAllocation.trim();
    }

    public Date getFirstInvestmentTime() {
        return firstInvestmentTime;
    }

    public void setFirstInvestmentTime(Date firstInvestmentTime) {
        this.firstInvestmentTime = firstInvestmentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sourceMemberId=").append(sourceMemberId);
        sb.append(", memberChannelType=").append(memberChannelType);
        sb.append(", level=").append(level);
        sb.append(", countAum=").append(countAum);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", isAllocation=").append(isAllocation);
        sb.append(", firstInvestmentTime=").append(firstInvestmentTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}