package com.ebsoft.ebstudytemplates2week.global.action;

import com.ebsoft.ebstudytemplates2week.domain.board.api.action.BoardWriteAction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionFactory {

  @Getter
  private static final ActionFactory instance = new ActionFactory();

  /**
   * command에 url을 넣어, 해당 url에 맞는 Action 반환
   * 추후 추가되는 url에 관한 로직 추가 가능 변경 X
   *
   * @param command
   * @return action
   */
  public Action getAction(String command) {
    Action action = null;

    if (command.equals("write")) {
      action = new BoardWriteAction();
    } else {
      log.warn("URL mapping 실패");
    }
    return action;
  }
}
