package com.yinlu.bi.system.constants;

import java.util.regex.Pattern;

/**
 * 系统常量
 * @author dzhao1
 */
public interface Constants {
  /**
   * 有效数据标识
   */
  int STATUS=0;
  /**
   * 菜单层级初始化
   * 0为一级菜单
   */
  int LEVEL=0;
  String BAR_MARK ="#";
  String NULL="null";
  String URL ="(https?|http)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
}
