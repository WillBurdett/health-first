package com.healthfirst.emailservice.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

  private String name;
  private String email;
  private List<ClassInfo> classes;

  public String formatEmail(){
    return String.format("Hello %s!\n\nWelcome to Health First Fitness Centre.\nBased on your interests we thought "
        + "we'd let you know about the following classes:\n\n%s\n\nWe look forward to seeing you soon!\nThe Health"
            + " First Team",
        this.name, this.classes);
  }


}
