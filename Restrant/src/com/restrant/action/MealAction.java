package com.restrant.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.MealBiz;
import com.restrant.biz.MealSeriesBiz;
import com.restrant.entity.Meal;
import com.restrant.entity.Pager;
import org.apache.commons.io.FileUtils;

public class MealAction extends ActionSupport implements RequestAware {
	
	//定义Meal类型属性，用于封装表单参数
	private Meal meal;	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	MealBiz mealBiz;
	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}
	MealSeriesBiz mealSeriesBiz;	
	public void setMealSeriesBiz(MealSeriesBiz mealSeriesBiz) {
		this.mealSeriesBiz = mealSeriesBiz;
	}
	//分页实体类
	private Pager pager;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	private File doc;                //封装上传文件的属性
	private String docFileName;      //封装上传文件的名称属性
	private String docContentType;   //封装上传文件的类型属性
	
	Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;		
	}
	
	//获取指定页码、符合查询条件的餐品列表，再转到餐品显示页show.jsp
	public String toShowMeal() throws Exception {		
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List mealList=null;		
		if(meal!=null){  
			//meal不为空，表示表单中输入了查询条件，此时将获取指定页码、符合查询条件的餐品列表
			mealList=mealBiz.getMealByCondition(meal, curPage);
			//统计符合查询条件的餐品数量,初始化分页类Pager对象，设置perPageRows和rowCount属性
			pager=mealBiz.getPagerOfMeal(meal);
			//将查询条件存入request范围，将作为分页超链接中的参数值
			if((meal.getMealseries()!=null) && (meal.getMealseries().getSeriesId()!=null))
				request.put("seriesId", new Integer(meal.getMealseries().getSeriesId()) );
		    if((meal.getMealName()!=null) && !meal.getMealName().equals(""))
				request.put("mealName", meal.getMealName());
		}else{    
			//meal为空，表示没有指定查询条件，此时将获取指定页码的餐品列表
			mealList=mealBiz.getAllMeal(curPage);
			//获取所有菜品数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
			pager=mealBiz.getPagerOfMeal();
		}
		
		//设置Pager对象中的待显示页页码
		pager.setCurPage(curPage);
		//将查询获得的列表存入request范围
		request.put("mealList", mealList);
		//获取菜系列表，存入request范围
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);		
		return "toShowMeal";
	}
	
	public String toShowDetails() throws Exception {		
		Meal aMeal=mealBiz.getMealByMealId(meal.getMealId());
		request.put("aMeal", aMeal);		
		return "toShowDetails";
	}
	
	//获取菜系列表，再转到添加餐品页addMeal.jsp
	public String toAddMeal() throws Exception {		
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);	
		return "addMeal";
	}
	
	//上传餐品图片、添加餐品信息，再转到toShowMeal
	public String doAddMeal() throws Exception {
		if(docFileName!=null){     //判断是否选择了上传图片
			// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建        
		    String targetDirectory = ServletActionContext.getServletContext().getRealPath("/mealimages");          
		    //重命名上传文件         
		    String targetFileName = generateFileName(docFileName);          
		    //在指定目录创建文件          
		    File target = new File(targetDirectory, targetFileName);          
		    //把要上传的文件copy过去         
		    FileUtils.copyFile(doc, target); 		
			meal.setMealImage(targetFileName);
			mealBiz.addMeal(meal);
		}		
		return "toShowMeal";
	}
	
	//重命名上传文件    
    public String generateFileName(String fileName){          
    	String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date());          
    	int random = new Random().nextInt(10000);          
    	int position = fileName.lastIndexOf(".");          
    	String extension = fileName.substring(position);           
    	return formatDate + random + extension;      
    }    
    
    
    //获取指定页码、符合查询条件的餐品列表，再转到菜品管理页managemeal.jsp
    public String toManageMeal() throws Exception {		
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List mealList=null;		
		if(meal!=null){
			mealList=mealBiz.getMealByCondition(meal, curPage);
			pager=mealBiz.getPagerOfMeal(meal);
			if((meal.getMealseries()!=null) && (meal.getMealseries().getSeriesId()!=null))
				request.put("seriesId", new Integer(meal.getMealseries().getSeriesId()) );
		    if((meal.getMealName()!=null) && !meal.getMealName().equals(""))
				request.put("mealName", meal.getMealName());
		}else{
			mealList=mealBiz.getAllMeal(curPage);
			//获取所有菜品数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
			pager=mealBiz.getPagerOfMeal();
		}		
		//设置Pager对象中的待显示页页码
		pager.setCurPage(curPage);
		request.put("mealList", mealList);
		//获取菜系列表，存入request范围
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);		
		return "managemeal";
	}
    
    //获取要修改的餐品对象，存入request范围，再转到餐品信息修改页
    public String toUpdateMeal() throws Exception {	
    	//获取要修改的餐品对象，存入request范围
    	Meal updatedMeal=mealBiz.getMealByMealId(meal.getMealId());
    	request.put("updatedMeal", updatedMeal);		
    	//获取菜系列表，存入request范围
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);	
		return "updateMeal";
    }
    
    //执行餐品信息修改，再转到toShowMeal
    public String doUpdateMeal() throws Exception {	
    	if(docFileName!=null){
	    	// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建        
		    String targetDirectory = ServletActionContext.getServletContext().getRealPath("/mealimages");          
		    //重命名上传文件         
		    String targetFileName = generateFileName(docFileName);          
		    //在指定目录创建文件          
		    File target = new File(targetDirectory, targetFileName);          
		    //把要上传的文件copy过去         
		    FileUtils.copyFile(doc, target); 		
		    //修改餐品图片
			meal.setMealImage(targetFileName);
    	}
		//更新餐品对象
		mealBiz.updateMeal(meal);
		return "toShowMeal";    	
    } 
    
    //处理删除餐品对象请求，再转到toManageMeal
    public String deleteMeal() throws Exception {	
    	mealBiz.deleteMeal(meal.getMealId());
    	return "toManageMeal";
    }
	
	
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getDocContentType() {
		return docContentType;
	}
	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

}
