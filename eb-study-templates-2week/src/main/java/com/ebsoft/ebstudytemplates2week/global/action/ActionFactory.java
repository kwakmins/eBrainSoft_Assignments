package com.ebsoft.ebstudytemplates2week.global.action;

import com.ebsoft.ebstudytemplates2week.domain.board.api.action.BoardForm;
import com.ebsoft.ebstudytemplates2week.domain.board.api.action.BoardWriteACtion;
import com.ebsoft.ebstudytemplates2week.domain.board.api.action.BoardWriteForm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionFactory {

  @Getter
  private static final ActionFactory instance = new ActionFactory();

  /**
   * command에 url을 넣어, 해당 url에 맞는 Action 반환 추후 추가되는 url에 관한 로직 추가 가능 변경 X
   *
   * @param command
   * @return action
   */
  public Action getAction(String command) {
    Action action = null;

    if (command.equals("write")) {
      action = new BoardWriteForm();
    } else if (command.equals("boardWriteAction")) {
      action = new BoardWriteACtion();
    } else if (command.equals("board")) {
      action = new BoardForm();
    } else {
      log.warn("URL mapping 실패");
    }
    return action;
  }
}
