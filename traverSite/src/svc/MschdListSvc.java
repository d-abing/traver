package svc;

import static db.JdbcUtil.*;
import java.util.*; 
import java.sql.*;
import dao.*;
import vo.*;

public class MschdListSvc {
   public ArrayList<ScheduleInfo> getMschdList(String where, String orderBy) {
        ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>();
        Connection conn = getConnection();
        MschdDao mschdDao = MschdDao.getInstance();
        mschdDao.setConnection(conn);
        
        scheduleList = mschdDao.getMschdList(where, orderBy);
        close(conn);
        
        return scheduleList;
        
    }
   
}
