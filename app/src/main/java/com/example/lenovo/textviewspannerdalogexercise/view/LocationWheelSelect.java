/**   
* @Title: LocationWheelSelect.java
* @Package com.bohan.ems.ui.viewutils
* @Description: TODO
* @author A18ccms A18ccms_gmail_com   
* @date 2016年8月3日 上午9:20:47
* @version V1.0   
*/
package com.example.lenovo.textviewspannerdalogexercise.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;


import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.LocationBean;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.OnWheelChangedListener;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.OnWheelScrollListener;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.WheelView;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.wheelviewadapter.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LocationWheelSelect
 *
 * @Description: TODO
 *
 * @author walker
 *
 * @date 2016年8月3日 上午9:20:47
 * 
 */
public class LocationWheelSelect implements OnWheelScrollListener, OnWheelChangedListener {

	/** 省 */
	private WheelView province;
	/** 市 */
	private WheelView city;
	/** 县 */
	private WheelView county;
	LocationCallback locationCallback;
	private TextView tvTitle;
	static PopupWindow menuWindow;
	/** 默认显示：省 */
	String strSelectProvince;
	/** 默认显示：市 */
	String strSelectCity;
	/** 默认显示：县 */
	String strSelectCounty;
	/** 当前选择市 */
	String strCurrentProvince;
	/** 当前选择市 */
	String strCurrentCity;
	/** 当前选择县 */
	String strCurrentCounty;
	/** 选择器显示条目数量：默认为7 */
	int dispalyItemNum = 7;
//	/** 选中文本大小 */
//	private int maxsize = 20;
//	/** 未选中文本大小 */
//	private int minsize = 20;
	/** 省选择器适配器 */
	private AddressTextAdapter provinceAdapter;
	/** 市选择器适配器 */
	private AddressTextAdapter cityAdapter;
	/** 县区选择器适配器 */
	private AddressTextAdapter countyAdapter;
	/** 显示地区实体 */
	LocationBean locationBean;
	/** 当前显示区县集合 */
	private ArrayList<String> countyList;
	/** 当前显示城市集合 */
	private ArrayList<String> cityList;
	/** 滚轮文本显示大小*/
	int textSize = 16;
	Activity activity;
	private TextView tvCancel;

	/***
	 * @Description: TODO 显示地址三级联动选择器：如果传入数据实体无数据，无数据位置显示“无数据”
	 * @param activity
	 *            调用选择器所在Activity实例
	 * @param locationBean
	 *            选择器显示数据实体
	 * @param title
	 *            当前选择器显示标题
	 * @param rootView
	 *            调用选择器所在界面内任意一View控件
	 * @param locationCallback
	 *            地址选择回调
	 * @param showLocation
	 *            弹出窗体显示位置 默认从窗体底部向上显示， 1-在指定控件下面显示
	 * @return
	 */
	public View getLocation(Activity activity, int showLocation, int displayItemCount, final LocationBean locationBean,
			String title, View rootView, final LocationCallback locationCallback) {
		this.locationBean = locationBean;
		this.locationCallback = locationCallback;
		this.activity = activity;
//		View view = View.inflate(Application.getInstance(), R.layout.locationpick, null);
        View view = View.inflate(activity,R.layout.locationpick, null);
		province = (WheelView) view.findViewById(R.id.wl_province);
		city = (WheelView) view.findViewById(R.id.wl_city);
		county = (WheelView) view.findViewById(R.id.wl_county);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		float height = metrics.heightPixels;
		float width = metrics.widthPixels;

		if (height / width > 1.5) {
//			dispalyItemNum = 7;
			textSize = 16;
		} else {
			textSize = 16;
//			dispalyItemNum = 5;
		}

		if (displayItemCount != 0) {
			dispalyItemNum = displayItemCount;
		}
		this.strSelectCity = locationBean.selectCity;
		this.strSelectProvince = locationBean.selectProvince;
		this.strSelectCounty = locationBean.selectCounty;
		tvTitle.setText(title + "");

		setProvinceAdapter();
		province.addScrollingListener(this);
		province.addChangingListener(this);
		city.addScrollingListener(this);
		city.addChangingListener(this);
		county.addScrollingListener(this);
		county.addChangingListener(this);

		menuWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//		menuWindow.setAnimationStyle(R.style.common_spin_dialog_anim);
		menuWindow.setFocusable(true);
		menuWindow.setWidth(LayoutParams.MATCH_PARENT);
		backgroundAlpha(0.5f);
		if (showLocation == 1) {
			menuWindow.showAsDropDown(rootView, 0, 0);
		} else {
			menuWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
		}
		menuWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				backgroundAlpha(1.0f);
				menuWindow = null;
			}
		});
		city.setFocusable(true);
		city.setFocusableInTouchMode(true);
		city.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
					confirm(-1);
				}

				if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
					// confirm();
				}
				return false;
			}
		});

		tvCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				confirm(-1);
			}
		});

		view.findViewById(R.id.tv_confirm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!isSelecting)
					confirm(0);
			}
		});
		return view;
	}

	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		activity.getWindow().setAttributes(lp);
	}

	private String xzqh;

	private void confirm(int flag) {
		try {
			strCurrentCity = cityAdapter.getItemText(city.getCurrentItem()) + "";
			strCurrentProvince = provinceAdapter.getItemText(province.getCurrentItem()) + "";
			strCurrentCounty = countyAdapter.getItemText(county.getCurrentItem()) + "";

			if ("无数据".equals(strCurrentCity)) {
				strCurrentCity = "";
			}
			if ("无数据".equals(strCurrentProvince)) {
				strCurrentProvince = "";
			}
			if ("无数据".equals(strCurrentCounty)) {
				strCurrentCounty = "";
			}

			// List<String> xzqhList =
			// locationBean.xzqhMap.get(locationBean.provinceList.get(province.getCurrentItem())+""+cityAdapter.getItemText(city.getCurrentItem())+"xzqh");
			try {
				xzqh = locationBean.xzqhMap.get(locationBean.provinceList.get(province.getCurrentItem()) + ""
						+ cityAdapter.getItemText(city.getCurrentItem()) + "xzqh").get(county.getCurrentItem());
			} catch (Exception e) {
				xzqh = "";
			}
		} catch (Exception e) {
			strCurrentCity = "";
			strCurrentProvince = "";
			strCurrentCounty = "";
		}
		if (flag == 0) {
			locationCallback.selectLocation(strCurrentProvince, strCurrentCity, strCurrentCounty, xzqh);
		} else {
			locationCallback.selectLcancel(strCurrentProvince, strCurrentCity, strCurrentCounty, xzqh);
		}
		if(menuWindow != null){
			menuWindow.dismiss();
		}
	}
	/**是否正在选择地址：正在选择的时候点击完成无效*/
	boolean isSelecting;
	@Override
	public void onScrollingStarted(WheelView wheel) {

	}

	/** 滚动时调用：更新选中文本样式 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		isSelecting = true;
		switch (wheel.getId()) {
		case R.id.wl_province:
			setTextviewSize(getCurrentSelectData(1), provinceAdapter, province.getCurrentItem(),
					locationBean.provinceList);
			break;
		case R.id.wl_city:
			setTextviewSize(getCurrentSelectData(2), cityAdapter, city.getCurrentItem(), cityList);
			break;
		case R.id.wl_county:
			setTextviewSize(getCurrentSelectData(3), countyAdapter, county.getCurrentItem(), countyList);
			break;

		default:
			break;
		}
	}

	/** 滚轮滚动结束后调用 */
	@Override
	public void onScrollingFinished(WheelView wheel) {
		isSelecting = false;
		switch (wheel.getId()) {
		case R.id.wl_province:
			setCityAdapter();
			setTextviewSize(getCurrentSelectData(1), provinceAdapter, province.getCurrentItem(),
					locationBean.provinceList);
			break;
		case R.id.wl_city:
			setCountyAdapter();
			setTextviewSize(getCurrentSelectData(2), cityAdapter, city.getCurrentItem(), cityList);
			break;
		case R.id.wl_county:
			setTextviewSize(getCurrentSelectData(3), countyAdapter, county.getCurrentItem(), countyList);
			break;

		default:
			break;
		}
	}

	/**
	 * @Description: TODO 获取当前选择器获取的数据
	 * @param i
	 *            1-获取当前选择的省 2-获取当前选择的市 3-获取当前选择的县区
	 */
	private String getCurrentSelectData(int i) {
		String res = "";
		switch (i) {
		case 1:
			try {
				res = locationBean.provinceList.get(province.getCurrentItem());
			} catch (Exception e) {
				res = locationBean.provinceList.get(0);
			}
			if (StringUtils.isEmptyUnNull(res)) {
				res = locationBean.provinceList.get(0);
			}
			break;
		case 2:
			try {
				res = cityList.get(city.getCurrentItem());
			} catch (Exception e) {
				res = cityList.get(0);
			}
			if (StringUtils.isEmptyUnNull(res)) {
				res = cityList.get(0);
			}
			break;
		case 3:
			try {
				res = countyList.get(county.getCurrentItem());
			} catch (Exception e) {
				res = countyList.get(0);
			}
			if (StringUtils.isEmptyUnNull(res)) {
				res = countyList.get(0);
			}
			break;

		default:
			break;
		}
		return res;
	}

	/**
	 * @Description: TODO 获取当前已选择条目的索引，出现异常返回0
	 * @param flag
	 * @param listData
	 * @return
	 */
	private int getSelectedIndex(int flag, List<String> listData) {
		// 省份已经设置适配器初始化选择省
		if (provinceAdapter != null && province != null) {
			strCurrentProvince = getCurrentSelectData(1);
		}

		switch (flag) {
		case 1:// 省
			try {
				for (int i = 0; i < locationBean.provinceList.size(); i++) {
					if (strSelectProvince.equals(locationBean.provinceList.get(i))) {
						return i;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}

			break;
		case 2:// 市
			try {
				if (cityAdapter != null && city != null && cityList != null && cityList.size() > city.getCurrentItem()
						&& !StringUtils.isContainEmpty(strSelectProvince, strSelectCity, strCurrentProvince)
						&& strCurrentProvince.equals(strSelectProvince)) {
					strCurrentCity = cityList.get(city.getCurrentItem());
				} else {
					return 0;
				}
				for (int i = 0; i < cityList.size(); i++) {
					if (strSelectCity.equals(cityList.get(i))) {
						return i;
					}
				}
			} catch (Exception e) {
				return 0;
			}

			break;
		case 3:// 县
			try {
				if (countyAdapter != null && county != null && countyList != null
						&& countyList.size() > county.getCurrentItem()) {
					strCurrentCounty = countyList.get(county.getCurrentItem());
				} else {
					return 0;
				}
				if (StringUtils.isContainEmpty(strSelectProvince, strSelectCity, strSelectCounty)) {
					return 0;
				}
				for (int i = 0; i < listData.size(); i++) {
					if (strSelectCounty.equals(listData.get(i))) {
						return i;
					}
				}
			} catch (Exception e) {
				return 0;
			}

			break;

		default:
			break;
		}

		return 0;
	}

	/** 省级选择器适配器设置 */
	private void setProvinceAdapter() {
		if (locationBean.provinceList == null || locationBean.provinceList.size() <= 0) {
			locationBean.provinceList = new ArrayList<String>();
			locationBean.provinceList.add("无数据");
		}
		provinceAdapter = new AddressTextAdapter(activity, locationBean.provinceList,
				getSelectedIndex(1, locationBean.provinceList), textSize, textSize, 2);
		provinceAdapter.setTextSize(textSize);
		province.setVisibleItems(dispalyItemNum);
		province.setViewAdapter(provinceAdapter);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				province.setCurrentItem(getSelectedIndex(1, locationBean.provinceList));
				province.scroll(0, 0);
			}
		}, 10);
	}

	/** 设置县区级选择器适配器 */
	private void setCountyAdapter() {
		countyList = locationBean.cityMap.get(locationBean.provinceList.get(province.getCurrentItem()) + ""
				+ cityAdapter.getItemText(city.getCurrentItem()));
		if (countyList == null || countyList.size() <= 0) {
			countyList = new ArrayList<String>();
			countyList.add("无数据");
		}
		countyAdapter = new AddressTextAdapter(activity, countyList, getSelectedIndex(3, countyList), textSize,
				textSize, 2);
		countyAdapter.setTextSize(textSize);
		county.setVisibleItems(dispalyItemNum);
		county.setViewAdapter(countyAdapter);
		county.setCurrentItem(getSelectedIndex(3, countyList));
		county.scroll(0, 0);

	}

	/** 设置市级选择器适配器 */
	private void setCityAdapter() {
		cityList = locationBean.provinceMap.get(provinceAdapter.getItemText(province.getCurrentItem()));
		if (cityList == null || cityList.size() <= 0) {
			cityList = new ArrayList<String>();
			cityList.add("无数据");
		}
		cityAdapter = new AddressTextAdapter(activity, cityList, getSelectedIndex(2, cityList), textSize, textSize, 2);
		cityAdapter.setTextSize(textSize);
		city.setVisibleItems(dispalyItemNum);
		city.setViewAdapter(cityAdapter);
		city.setCurrentItem(getSelectedIndex(2, cityList));
		city.scroll(0, 0);

	}

	/**
	 * 设置字体大小 出现异常直接抛出，不影响程序运行
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter, int selectIndex,
			List<String> dataList) {
		try {
			ArrayList<View> arrayList = adapter.getTestViews();
			int size = arrayList.size();
			String currentText;
			for (int i = 0; i < size; i++) {
				TextView textvew = (TextView) arrayList.get(i);
				currentText = textvew.getText().toString();
				// System.out.println("设置条目样式： " + currentText + "\t-" + i + " -
				// index - " + selectIndex);
				/*
				 * 判定是否为选中条目： 1、当前选中位置文本：curriteItemText 2、遍历所有的View，获取上面显示的文本
				 * 3、判断如果 view显示文本与集合中文本相同，则设置当前条目为选中样式 4、如果当前 view显示文本长度大于等于4
				 * 并且选中的文本长度大于View文本长度，view显示部分文本，判断选中文本是否以view文本开头，
				 * 是的时候设置当前View为选中样式
				 */
				if (!StringUtils.isEmptyUnNull(curriteItemText) && !StringUtils.isEmptyUnNull(currentText)
						&& curriteItemText.equals(currentText)
				/*
				 * || (currentText.length() >=4 &&curriteItemText.length() >
				 * currentText.length() &&
				 * curriteItemText.startsWith(currentText))
				 */) {
					textvew.setTextSize(textSize);
//					textvew.setTextColor(0xff333333);
					textvew.setTextColor(0xff32bf86);
				} else {
					textvew.setTextColor(0xff666666);
					textvew.setTextSize(textSize);
				}

				// if(index/2 ==0){
				// textView.setBackgroundColor(0xffD8EAF9);
				// }else{
				// textView.setBackgroundColor(0xffDCDADC);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public interface LocationCallback {
		/**
		 * @Description: TODO 选取城市回调
		 * @param province
		 *            省
		 * @param city
		 *            市
		 * @param county
		 *            县
		 * @param xzqh
		 *            行政区划代码
		 */
		public void selectLocation(String province, String city, String county, String xzqh);

		/**
		 * @Description: TODO 根据标记获取当前显示省市县数据
		 * @param province
		 *            1-省 2-市 3-县区
		 * @param city
		 * @param county
		 * @param xzqh
		 */
		// public List<String> getData(int flag, String name);
		public void selectLcancel(String province, String city, String county, String xzqh);
	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize,
				int minsize, int location) {
			super(context, R.layout.item_wheel, NO_RESOURCE, currentItem, maxsize, minsize, location);
//            super(context, R.layout.item_wheel, NO_RESOURCE);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			try {
				return list.size();
			} catch (Exception e) {
				return 0;
			}
		}

		@Override
		protected CharSequence getItemText(int index) {
			try {
				return list.get(index) + "";
			} catch (Exception e) {
				if (list != null && list.size() > 0) {
					return list.get(0);
				} else {
					return "";
				}
			}
		}
	}

}
