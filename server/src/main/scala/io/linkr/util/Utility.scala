package io.linkr.util

import java.sql.Date
import java.util.Calendar

/**
  * Created by lehel on 1/21/2017.
  */
object Utility {

  def currentTime():Date=new Date(Calendar.getInstance().getTime().getTime());

}
