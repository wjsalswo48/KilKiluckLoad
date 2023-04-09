package mz.sixsense.board;

import lombok.Data;

@Data
public class Search {

    private String searchCategory;
    private String searchCondition;
    private String searchKeyword;

    private int page;

}