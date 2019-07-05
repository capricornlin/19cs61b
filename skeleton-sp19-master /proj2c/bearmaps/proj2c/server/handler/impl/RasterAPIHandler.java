package bearmaps.proj2c.server.handler.impl;

import bearmaps.proj2c.AugmentedStreetMapGraph;
import bearmaps.proj2c.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;
import bearmaps.proj2c.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static bearmaps.proj2c.utils.Constants.SEMANTIC_STREET_GRAPH;
import static bearmaps.proj2c.utils.Constants.ROUTE_LIST;

/**
 * Handles requests from the web browser for map images. These images
 * will be rastered into one large image to be displayed to the user.
 * @author rahul, Josh Hug, _________
 */
public class RasterAPIHandler extends APIRouteHandler<Map<String, Double>, Map<String, Object>> {



    /**
     * Each raster request to the server will have the following parameters
     * as keys in the params map accessible by,
     * i.e., params.get("ullat") inside RasterAPIHandler.processRequest(). <br>
     * ullat : upper left corner latitude, <br> ullon : upper left corner longitude, <br>
     * lrlat : lower right corner latitude,<br> lrlon : lower right corner longitude <br>
     * w : user viewport window width in pixels,<br> h : user viewport height in pixels.
     **/
    private static final String[] REQUIRED_RASTER_REQUEST_PARAMS = {"ullat", "ullon", "lrlat",
            "lrlon", "w", "h"};

    /**
     * The result of rastering must be a map containing all of the
     * fields listed in the comments for RasterAPIHandler.processRequest.
     **/
    private static final String[] REQUIRED_RASTER_RESULT_PARAMS = {"render_grid", "raster_ul_lon",
            "raster_ul_lat", "raster_lr_lon", "raster_lr_lat", "depth", "query_success"};


    @Override
    protected Map<String, Double> parseRequestParams(Request request) {
        return getRequestParams(request, REQUIRED_RASTER_REQUEST_PARAMS);
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param requestParams Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @param response : Not used by this function. You may ignore.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image;
     *                    can also be interpreted as the length of the numbers in the image
     *                    string. <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */

    @Override
    public Map<String, Object> processRequest(Map<String, Double> requestParams, Response response) {
        System.out.println("yo, wanna know the parameters given by the web browser? They are:");
        System.out.println(requestParams);



        double raster_londpp = get_rasterlondpp(requestParams);
        //System.out.println("raster resolution is "+raster_londpp + " feet per pixel.");

        /** Bounding box is the d0_x0_y0, which is depth level 0 */
        double bounding_lonapp = getboundingbox();
        //System.out.println("bounding box resolution is "+bounding_lonapp + " feet per pixel in depth 0.");

        int a = depthlevel(raster_londpp,bounding_lonapp);
        //System.out.println("We need depth level "+ a +" feet per pixel");

        /** find the depth level K's dK_x0_y0 */
        double b = findlrlon(Constants.ROOT_ULLON,Constants.ROOT_LRLON,a,bounding_lonapp);
        double d = findlrlat(a);
        double c = findullon(b,bounding_lonapp,a);
        double e = findullat();
        //System.out.println("d"+ a +"_x0_y0 lrlon: "+ b);
        //System.out.println("d"+ a + "_x0_y0 lrlat: "+ d);
        //System.out.println("d"+ a + "_x0_y0 ullon: "+ c);
        //System.out.println("d"+ a + "_x0_y0 ullat: "+ e);

        /** find the raster box */
        int f = find_raster_xk(requestParams.get("ullon"),b,a);
        int g = find_raster_xk(requestParams.get("lrlon"),b,a);
        int h = find_raster_yk(requestParams.get("ullat"),d,a);
        int j = find_raster_yk(requestParams.get("lrlat"),d,a);
        //System.out.println("x_k_left = "+f);
        //System.out.println("x_k_right = "+g);
        //System.out.println("y_k_left = "+h);
        //System.out.println("y_k_right = "+j);
        double raster_ullon = find_raster_lon(c,a,f);
        double raster_ullat = find_raster_lat(e,a,h);
        double raster_lrlon = find_raster_lon(b,a,g);
        double raster_lrlat = find_raster_lat(d,a,j);
        //System.out.println(raster_ullon);
        //System.out.println(raster_ullat);
        //System.out.println(raster_lrlon);
        //System.out.println(raster_lrlat);


        /** Print the render_grid */
        String[][] render_grid = new String[j-h+1][g-f+1];
        List<String> k = rasterbox(f,g,h,j,a,render_grid);
        for(int i = 0;i<k.size();i+=1){
            //System.out.println(k.get(i));
        }


        Map<String, Object> results = new HashMap<>();

        results.put("raster_ul_lon",raster_ullon);
        results.put("depth",a);
        results.put("raster_lr_lon",raster_lrlon);
        results.put("raster_lr_lat",raster_lrlat);
        results.put("raster_ul_lat",raster_ullat);
        results.put("query_success",true);
        results.put("render_grid",render_grid);

        //System.out.println("Since you haven't implemented RasterAPIHandler.processRequest, nothing is displayed in "
               // + "your browser.");
        return results;
    }

    /** Find raster resolution */
    public double get_rasterlondpp(Map<String, Double> requestParams){
        double ullon = requestParams.get("ullon");
        double lrlon = requestParams.get("lrlon");
        double londpp = ((lrlon - ullon)/requestParams.get("w"))*288200;
        return londpp;
    }

    /** Find bounding box resolution */
    public double getboundingbox(){
        double LonDPP = ((Constants.ROOT_LRLON - Constants.ROOT_ULLON)/Constants.TILE_SIZE)*288200;
        return LonDPP;
    }

    /** Find depth level we need to match raster resolution */
    public int depthlevel(double londpp,double bondingbox){
        for(int i = 1;i <=7;i+=1){
            if(bondingbox/Math.pow(2,i) <= londpp){
                return i;
            }
        }
        return 7;
    }

    public double find_raster_lon(double origin_ullon,int depth,int x){
        double k = (Constants.ROOT_LRLON - Constants.ROOT_ULLON)/256;
        double resolution = k/Math.pow(2,depth)*256;
        origin_ullon+=resolution*x;
        return origin_ullon;
        //return resolution;
    }
    public double find_raster_lat(double origin_ullat,int depth,int y){
        double x = (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT)/256;
        double resolution = x/Math.pow(2,depth)*256;
        origin_ullat-=resolution*y;
        return  origin_ullat;
    }
    /***
    public double find_raster_lrlon(double origin_lrlon,int depth,int x,double boundlondpp){
        double resolution = boundlondpp/Math.pow(2,depth);
        origin_lrlon+=resolution*x;
        return origin_lrlon;
    }
    public double find_raster_lrlat(double origin_lrlat,int depth,int y){
        double x = (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT)/256;
        double resolution = x/Math.pow(2,depth);
        origin_lrlat-=resolution*y;
        return  origin_lrlat;
    }
     */


    /** Find the lrlon in k depth level */
    public double findlrlon(double boudingullon,double boundinglrlon,int depth,double bounding_lonapp){
        double ullon = boudingullon;
        double lrlon = boundinglrlon;

        for(int i = 1;i <= depth;i+=1){
            double resolution = bounding_lonapp/Math.pow(2,i);
            double a = resolution*Constants.TILE_SIZE/288200;
            lrlon = a + ullon;
        }
        return lrlon;
    }

    public double findlrlat(double depth){
        double x = (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT)/256;
        double resolution = x/Math.pow(2,depth);
        double lrlat = Constants.ROOT_ULLAT - resolution*256;
        return lrlat;
    }


    /** Find the ullon in k depth level */
    public double findullon(double lrlon,double londpp,int depth){
        double resolution = londpp/Math.pow(2,depth);
        double ullon = lrlon - resolution*Constants.TILE_SIZE/288200;
        return ullon;
    }

    public double findullat(){
        return Constants.ROOT_ULLAT;
    }

    public int find_raster_xk(double querylon,double lrlon,double depth){
        double x = (Constants.ROOT_LRLON - Constants.ROOT_ULLON)/256;
        double resolution = x/Math.pow(2,depth);
        double newlrlon = lrlon;
        int i = 0;
        while(newlrlon <= querylon){
            newlrlon+=resolution*256;
            i+=1;
        }
        return i;
    }

    public int find_raster_yk(double querylat,double lrlat,double depth){
        double x = (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT)/256;
        double resolution = x/Math.pow(2,depth);
        double newlrlat = lrlat;
        int i = 0;
        while(newlrlat >= querylat){
            newlrlat-=resolution*256;
            i+=1;
        }
        return i;
    }

    public List<String> rasterbox(int x_left,int x_right,int y_left,int y_right,int depth,String[][] render_grid){
        List<String> raster = new ArrayList<>();
        int x =0;
        int y =0;
        for(int i = y_left;i<= y_right;i+=1){
            for(int j = x_left;j<=x_right;j+=1){
                String a = "d"+ depth+"_x"+j+"_y"+i+".png";
                raster.add(a);
                render_grid[x][y] = a;
                y++;
            }
            y = 0;
            x++;
        }
        return raster;
    }

    @Override
    protected Object buildJsonResponse(Map<String, Object> result) {
        boolean rasterSuccess = validateRasteredImgParams(result);

        if (rasterSuccess) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writeImagesToOutputStream(result, os);
            String encodedImage = Base64.getEncoder().encodeToString(os.toByteArray());
            result.put("b64_encoded_image_data", encodedImage);
        }
        return super.buildJsonResponse(result);
    }

    private Map<String, Object> queryFail() {
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", null);
        results.put("raster_ul_lon", 0);
        results.put("raster_ul_lat", 0);
        results.put("raster_lr_lon", 0);
        results.put("raster_lr_lat", 0);
        results.put("depth", 0);
        results.put("query_success", false);
        return results;
    }

    /**
     * Validates that Rasterer has returned a result that can be rendered.
     * @param rip : Parameters provided by the rasterer
     */
    private boolean validateRasteredImgParams(Map<String, Object> rip) {
        for (String p : REQUIRED_RASTER_RESULT_PARAMS) {
            if (!rip.containsKey(p)) {
                System.out.println("Your rastering result is missing the " + p + " field.");
                return false;
            }
        }
        if (rip.containsKey("query_success")) {
            boolean success = (boolean) rip.get("query_success");
            if (!success) {
                System.out.println("query_success was reported as a failure");
                return false;
            }
        }
        return true;
    }

    /**
     * Writes the images corresponding to rasteredImgParams to the output stream.
     * In Spring 2016, students had to do this on their own, but in 2017,
     * we made this into provided code since it was just a bit too low level.
     */
    private  void writeImagesToOutputStream(Map<String, Object> rasteredImageParams,
                                                  ByteArrayOutputStream os) {
        String[][] renderGrid = (String[][]) rasteredImageParams.get("render_grid");
        int numVertTiles = renderGrid.length;
        int numHorizTiles = renderGrid[0].length;

        BufferedImage img = new BufferedImage(numHorizTiles * Constants.TILE_SIZE,
                numVertTiles * Constants.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = img.getGraphics();
        int x = 0, y = 0;

        for (int r = 0; r < numVertTiles; r += 1) {
            for (int c = 0; c < numHorizTiles; c += 1) {
                graphic.drawImage(getImage(Constants.IMG_ROOT + renderGrid[r][c]), x, y, null);
                x += Constants.TILE_SIZE;
                if (x >= img.getWidth()) {
                    x = 0;
                    y += Constants.TILE_SIZE;
                }
            }
        }

        /* If there is a route, draw it. */
        double ullon = (double) rasteredImageParams.get("raster_ul_lon"); //tiles.get(0).ulp;
        double ullat = (double) rasteredImageParams.get("raster_ul_lat"); //tiles.get(0).ulp;
        double lrlon = (double) rasteredImageParams.get("raster_lr_lon"); //tiles.get(0).ulp;
        double lrlat = (double) rasteredImageParams.get("raster_lr_lat"); //tiles.get(0).ulp;

        final double wdpp = (lrlon - ullon) / img.getWidth();
        final double hdpp = (ullat - lrlat) / img.getHeight();
        AugmentedStreetMapGraph graph = SEMANTIC_STREET_GRAPH;
        List<Long> route = ROUTE_LIST;

        if (route != null && !route.isEmpty()) {
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.setColor(Constants.ROUTE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(Constants.ROUTE_STROKE_WIDTH_PX,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            route.stream().reduce((v, w) -> {
                g2d.drawLine((int) ((graph.lon(v) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(v)) * (1 / hdpp)),
                        (int) ((graph.lon(w) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(w)) * (1 / hdpp)));
                return w;
            });
        }

        rasteredImageParams.put("raster_width", img.getWidth());
        rasteredImageParams.put("raster_height", img.getHeight());

        try {
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage getImage(String imgPath) {
        BufferedImage tileImg = null;
        if (tileImg == null) {
            try {
                File in = new File(imgPath);
                tileImg = ImageIO.read(in);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tileImg;
    }
}
