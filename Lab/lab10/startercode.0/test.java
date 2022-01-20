
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class test {
    public static void main(String[] args) {
        int pointer1 = 0;
        int pointer2 = 0;
        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        int m = 3;
        int n = 3;
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        int counter = 0;
        while(pointer1 < m && pointer2 < n){
            if (temp[pointer1] < nums2[pointer2]){
                nums1[counter] = temp[pointer1];
                pointer1++;
            }
            else {
                nums1[counter] = nums2[pointer2];
                pointer2++;
            }
            counter++;
        }
        if(pointer1 < m){
            for(int i = pointer1; i < m; i++){
                nums1[counter] = temp[i];
                counter++;
            }
        } else if(pointer2 < n){
            for (int i = pointer2; i < n;i++){
                nums1[counter] = nums2[i];
                counter++;
            }
        }
        for (int i =0; i < m+n; i++){
            System.out.println(nums1[i]);
        }
    }
}