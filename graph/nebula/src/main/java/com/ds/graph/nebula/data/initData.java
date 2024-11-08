package com.ds.graph.nebula.data;

import com.ds.graph.nebula.data.sj.D1GSJBXX;

import java.util.List;

/**
 * @author ds
 * @date 2024/11/8
 * @description
 */
public class initData {
    public static void main(String[] args) {
        NebulaRepo nebulaRepo = NebulaRepo.getNebulaRepo();
        String createSpace = "CREATE SPACE IF NOT EXISTS " + Config.GRAPH + " (vid_type=FIXED_STRING(64));";

        try {
            nebulaRepo.execute(createSpace);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        try {
            List<String> nqlList = D1GSJBXX.getNql();
            for (String nql : nqlList) {
                try {
                    nql = nql.replace("\n", "").replace("\t", "");
                    nebulaRepo.executeUseDatabase(nql);
                } catch (Exception e) {
                    System.out.println("e = " + e);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        } finally {
            nebulaRepo.close();
        }
    }
}
