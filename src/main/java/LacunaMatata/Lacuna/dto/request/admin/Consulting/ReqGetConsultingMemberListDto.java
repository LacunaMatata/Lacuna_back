package LacunaMatata.Lacuna.dto.request.admin.Consulting;

import lombok.Data;

@Data
public class ReqGetConsultingMemberListDto {
    private int filter;
    private int option;
    private String searchValue;
    private int page;
    private int limit;
}