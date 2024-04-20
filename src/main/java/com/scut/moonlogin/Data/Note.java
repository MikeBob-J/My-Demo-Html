package com.scut.moonlogin.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


// 完善Javabean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

       private String userName;
       private Integer id;
       private Date date;
       private String mood;
       private String note;
}
