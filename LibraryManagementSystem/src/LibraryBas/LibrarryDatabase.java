package LibraryBas;

import util.BooksTM;
import util.IssuedBookdTM;
import util.MemberTM;

import java.util.ArrayList;

public class LibrarryDatabase {

      public static ArrayList<MemberTM> memberTMS = new ArrayList<>();
      public static ArrayList<BooksTM> booksTMS = new ArrayList<>();
      public static  ArrayList<IssuedBookdTM> issuedBookdTMS = new ArrayList<>();

    static {

        memberTMS.add(new MemberTM("M001","Sasindu Avishka","Ratmalana",071111111));
        memberTMS.add(new MemberTM("M002","Kushara Wayomi","Matara",07222222));
        memberTMS.add(new MemberTM("M003","Suhad Mendis","Dehiwala",073333333));
    }

    static {
        booksTMS.add(new BooksTM("B001","The Ring","Danielle Steel","Available"));
        booksTMS.add(new BooksTM("B002","Dangerous Games","Danielle Steel","Available"));
        booksTMS.add(new BooksTM("B003","Harry Potter and the Order of the Phoenix","J.K.Rowlings","Available"));


    }




}


