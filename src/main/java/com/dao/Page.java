package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Package Name: com.mos.base.sql
 * User: grq
 * Date: 2016/3/25
 * Time: 9:08
 * Description:some des here!
 */
public class Page<T> {

    //涓�椤垫樉绀虹殑璁板綍鏁�
    private int numPerPage;
    //璁板綍鎬绘暟
    private int total;
    //鎬婚〉鏁�
    private int totalPages;
    //褰撳墠椤电爜
    private int currentPage;
    //璧峰琛屾暟
    private int startIndex;
    //缁撴潫琛屾暟
    private int lastIndex;
    //缁撴灉闆嗗瓨鏀綥ist
    private List<T> rows;
    public Page(){

    }
    public Page(int currentPage,int numPerPage,List<T> list){

        //璁剧疆姣忛〉鏄剧ず璁板綍鏁�
//        setNumPerPage(numPerPage);
        //璁剧疆瑕佹樉绀虹殑椤垫暟
        setCurrentPage(currentPage);
        //鎬昏褰曟暟
//        setTotal(list.isEmpty() ? 0 : list.size());
        //璁＄畻鎬婚〉鏁�
        setTotalPages();
        //璁＄畻璧峰琛屾暟
        setStartIndex();
        //璁＄畻缁撴潫琛屾暟
        setLastIndex();
        System.out.println("lastIndex="+lastIndex);
        //瑁呭叆缁撴灉闆�
        setRows(list);
//        setResultList(list);
    }
    public Page(int currentPage,int numPerPage,int totalRows,List<T> list){

        //璁剧疆姣忛〉鏄剧ず璁板綍鏁�
        setNumPerPage(numPerPage);
        //璁剧疆瑕佹樉绀虹殑椤垫暟
        setCurrentPage(currentPage);
        //鎬昏褰曟暟
        setTotal(totalRows);
//        setTotalRows(totalRows);
        //璁＄畻鎬婚〉鏁�
        setTotalPages();
        //璁＄畻璧峰琛屾暟
        setStartIndex();
        //璁＄畻缁撴潫琛屾暟
        setLastIndex();
        System.out.println("lastIndex="+lastIndex);
        //瑁呭叆缁撴灉闆�
        setRows(list);
//        setResultList(list);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalRows() {
        return total;
    }

    public void setTotalRows(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        if(total % numPerPage == 0){
            this.totalPages = total / numPerPage;
        }else{
            this.totalPages = (total / numPerPage) + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex() {
        this.startIndex = (currentPage - 1) * numPerPage;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex() {
        if( total < numPerPage){
            this.lastIndex = total;
        }else if((total % numPerPage == 0) || (total % numPerPage != 0 && currentPage < totalPages)){
            this.lastIndex = currentPage * numPerPage;
        }else if(total % numPerPage != 0 && currentPage == totalPages){//鏈�鍚庝竴椤�
            this.lastIndex = total ;
        }
    }

    public List<T> getResultList() {
        return rows;
    }

    public void setResultList(List<T> rows) {
        this.rows = rows;
    }
}
