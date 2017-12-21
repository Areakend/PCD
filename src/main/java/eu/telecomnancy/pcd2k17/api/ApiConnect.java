package eu.telecomnancy.pcd2k17.api;
import org.gitlab4j.api.*;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public final class ApiConnect {
    public static GitLabApi GLA;
    public static RepositoryApi REPOAPI;
    public static RepositoryFileApi REPOFILEAPI;
    public static GroupApi GROUP;
    public static ProjectApi PROJECT;
    public static UserApi USER;

    private String url;
    private static ApiConnect apico;

    public ApiConnect(String u){
        ApiConnect.apico = this;
        this.url = u;
    }

    public void login(){
        this.getToken();
        ApiConnect.GLA = new GitLabApi(this.url, this.getToken());
        ApiConnect.REPOAPI = new RepositoryApi(ApiConnect.GLA);
        ApiConnect.REPOFILEAPI = new RepositoryFileApi(ApiConnect.GLA);
        ApiConnect.GROUP = new GroupApi(ApiConnect.GLA);
        ApiConnect.PROJECT = new ProjectApi(ApiConnect.GLA);
        ApiConnect.USER = ApiConnect.GLA.getUserApi();
    }

    public void login(String tok){
        this.setToken(tok);
        this.login();
    }

    public boolean loginOK(){
        try{
            if (ApiConnect.GLA.getProjectApi().getProjects().size() >0){
                return true;
            }
        }
        catch (Exception e){
        }
        File file = new File(".token/userToken.txt");
        file.delete();
        return false;
    }

	@SuppressWarnings("resource")
	private String getToken(){
        String line ;
        BufferedReader in;

        try {
            File dir = new File(".token");
            dir.mkdir();
            in = new BufferedReader(new FileReader(".token/userToken.txt"));
            line = in.readLine();
            return line;
        }
        catch (Exception e){}
        return null;
    }

    public void setToken(String tok){
        try {
            PrintWriter writer = new PrintWriter(".token/userToken.txt", "UTF-8");
            writer.println(tok);
            writer.close();
        }
        catch (Exception ee){System.out.println("marche pas");}
    }

    public Group getDiscipline(String name){
        try {
            List<Group> group = ApiConnect.GROUP.getGroups();
            for (int i = 0 ; i<group.size();i++){
                if (group.get(i).getName().equals(name)){
                    return group.get(i);
                }
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get groups. "+e );
        }
        return null;
    }

    public List<String> getListDiscipline(){
        List<String> list = new LinkedList<>();
        try{
            for (Group gp:ApiConnect.GROUP.getGroups()) {
                if(gp.getParentId() == null){
                    list.add(gp.getName());
                }
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't show the disciplines. "+e);
        }
        return list;
    }

    public void showListDiscipline(){
        int i = 0;
        System.out.println("List of disciplines :");
        for (String s:this.getListDiscipline()) {
            System.out.println(s);
            i++;
        }
        System.out.println("Total : "+i+"\n");
    }

    public String getCurrentUserName(){
        try {
            return ApiConnect.USER.getCurrentUser().getName();
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get current user name. "+e);
        }
        return "";
    }

    public static ApiConnect getInstance(){
        return ApiConnect.apico;
    }

}