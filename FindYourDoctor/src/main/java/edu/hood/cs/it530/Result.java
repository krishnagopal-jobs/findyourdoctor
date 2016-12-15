/**
 * 
 */
package edu.hood.cs.it530;

import java.util.List;

/**
 * @author kisna
 *
 */
public class Result<T> {
    
    
    private List<T> data;
    
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }


}
