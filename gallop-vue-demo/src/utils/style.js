//const NAVIGATOR_HEIGHT = 44
const TAB_BAR_HEIGHT = 50

/**
 * 返回屏幕可用高度
 * // NOTE 各端返回的 windowHeight 不一定是最终可用高度（例如可能没减去 statusBar 的高度），需二次计算
 * @param {*} showTabBar
 */

export function getWindowHeight(showTabBar = true) {

  const windowHeight = `${document.documentElement.clientHeight}`
  const tabBarHeight = showTabBar ? TAB_BAR_HEIGHT : 0

  return `${windowHeight - tabBarHeight}px`
}

export function triggerEvent(element,eventNameStr){

  if (document.createEvent) {
    console.log("call triggerEvent......000000")
    // eslint-disable-next-line no-global-assign
    event = document.createEvent("HTMLEvents");
    event.initEvent(eventNameStr, true, true);
    event.eventName = eventNameStr;
    element.dispatchEvent(event);
  } else {
    console.log("call triggerEvent......")
    // eslint-disable-next-line no-global-assign
    event = document.createEventObject();
    event.eventType = eventNameStr;
    event.eventName = eventNameStr;
    element.fireEvent("on" + event.eventType, event);
  }
}
