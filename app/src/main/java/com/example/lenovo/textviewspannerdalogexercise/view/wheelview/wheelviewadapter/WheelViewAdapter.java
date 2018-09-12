/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.lenovo.textviewspannerdalogexercise.view.wheelview.wheelviewadapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Wheel items adapter interface
 */
public interface WheelViewAdapter {
	/**
	 * Gets items count
	 * @return the count of wheel items
	 */
	public int getItemsCount();
	
	/**
	 * Get a View that displays the data at the specified position in the data set
	 * 
	 * @param index the item index  item项目索引
	 * @param convertView the old view to reuse if possible
	 * @param parent the parent that this view will eventually be attached to  父控件
	 * @return the wheel item View
	 */
	public View getItem(int index, View convertView, ViewGroup parent);

	/**
	 * Get a View that displays an empty wheel item placed before the first or after
	 * the last wheel item.
	 * 使item首尾相接
	 *
	 * @param convertView the old view to reuse if possible
     * @param parent the parent that this view will eventually be attached to 父控件
	 * @return the empty item View
	 */
	public View getEmptyItem(View convertView, ViewGroup parent);
	
	/**
	 * Register an observer that is called when changes happen to the data used by this adapter.
	 * @param observer the observer to be registered
	 * 注册在适配器使用的数据发生变化时调用
	 */
	public void registerDataSetObserver(DataSetObserver observer);
	
	/**
	 * Unregister an observer that has previously been registered
	 * @param observer the observer to be unregistered
	 * 取消注册先前已注册的
	 */
	void unregisterDataSetObserver(DataSetObserver observer);//观察者模式
}
