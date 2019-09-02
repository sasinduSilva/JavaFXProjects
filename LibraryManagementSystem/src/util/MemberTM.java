package util;

public class MemberTM {

    private String memberId;
    private String memberName;
    private String memberAddress;
    private int memberCntctNo;

    public MemberTM() {
    }

    public MemberTM(String memberId, String memberName, String memberAddress, int memberCntctNo) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberCntctNo = memberCntctNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public int getMemberCntctNo() {
        return memberCntctNo;
    }

    public void setMemberCntctNo(int memberCntctNo) {
        this.memberCntctNo = memberCntctNo;
    }

    @Override
    public String toString() {
        return "MemberTM{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberCntctNo=" + memberCntctNo +
                '}';
    }
}
