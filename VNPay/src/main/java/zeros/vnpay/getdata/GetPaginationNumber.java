/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.vnpay.getdata;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class GetPaginationNumber {
    private static Logger log = LoggerFactory.getLogger("logback");
    public static List<Integer> paginationNumber(int page, int size) throws SQLException {
        try {
            int lastPage = GetData.countRecord() / size + 1;
            if (lastPage < 7) {
                List<Integer> result = new ArrayList();
                for (int i = 0; i < lastPage; i++) {
                    result.add(i + 1);
                }
                return result;
            } else if (lastPage >= 7) {
                List<Integer> result = new ArrayList();
                result.add(1);
                if (page - 1 > 2 && lastPage - page > 2) {
                    result.add(0);
                    result.add(page - 1);
                    result.add(page);
                    result.add(page + 1);
                    result.add(0);
                } else if (page - 1 <= 2) {
                    for (int i = 2; i <= page + 1; i++) {
                        result.add(i);
                    }
                    result.add(0);
                } else if (lastPage - page <= 2) {
                    result.add(0);
                    for (int i = page - 1; i < lastPage; i++) {
                        result.add(i);
                    }
                }
                result.add(lastPage);
                return result;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<Integer> ();
    }

    
}

