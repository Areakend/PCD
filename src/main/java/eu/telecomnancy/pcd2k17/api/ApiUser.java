package eu.telecomnancy.pcd2k17.api;

import org.gitlab4j.api.Pager;
import org.gitlab4j.api.UserApi;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import java.util.LinkedList;
import java.util.List;

public class ApiUser {
    private UserApi userApi;
    private List<User> listUser;

    public ApiUser (){
        this.userApi = ApiConnect.USER;
        this.listUser = this.getUsers();
    }

    public LinkedList<User> getUsers(){
        try {
            LinkedList<User> list = new LinkedList<>();
            Pager<User> pages = this.userApi.getUsers(100);
            pages.first();
            for (int i =0 ; i<pages.getTotalPages(); i++){
                for (User u:pages.current()) {
                    list.add(u);
                }
                if (pages.hasNext())pages.next();
            }
            return list;
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get the user list. "+e);
        }
        return null;
    }

    //id ; username ; email ; team

    public LinkedList<String> getListUserName(){
        LinkedList<String> list = new LinkedList<>();
        for (User u: this.listUser) {
            list.add(u.getUsername());
        }
        return list;
    }

    public LinkedList<String> getListEmail(){
        LinkedList<String> list = new LinkedList<>();
        for (User u: this.listUser) {
            list.add(u.getEmail());
        }
        return list;
    }

    public LinkedList<String> getListTeam(){
        LinkedList<String> list = new LinkedList<>();
        try{
            List<Project> projects = ApiConnect.PROJECT.getProjects();
            for (User u: this.listUser) {

                list.add(u.getUsername());
            }
        }
        catch (org.gitlab4j.api.GitLabApiException e){
            System.out.println("Internal Error : Can't get projects list. "+e);
        }
        return list;
    }

    public LinkedList<Integer> getListId(){
        LinkedList<Integer> list = new LinkedList<>();
        for (User u: this.listUser) {
            list.add(u.getId());
        }
        return list;
    }




}
