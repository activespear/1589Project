package miem.projects.vulnerabilities.MINOR.FB;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FB_MTIA_SUSPECT_STRUTS_INSTANCE_FIELD {
    public static void main(String[] args) {
        // Для тестирования можно использовать mock-объекты
        incorrectTest(null, null, null);
        correctTest(null, null, null);
    }

    // Некорректно: изменяемое поле экземпляра Action
    static class UserActionIncorrect extends Action {
        private String currentUser;  // Опасное поле экземпляра

        public ActionForward execute(ActionMapping mapping, 
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
            currentUser = request.getParameter("user");
            request.setAttribute("user", currentUser);
            return mapping.findForward("success");
        }
    }

    // Корректно: использование локальных переменных
    static class UserActionCorrect extends Action {
        public ActionForward execute(ActionMapping mapping, 
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
            String currentUser = request.getParameter("user");  // Локальная переменная
            request.setAttribute("user", currentUser);
            return mapping.findForward("success");
        }
    }

    public static void incorrectTest(ActionMapping mapping, 
                                   ActionForm form, 
                                   HttpServletRequest request) {
        if (request != null) {
            new UserActionIncorrect().execute(mapping, form, request, null);
        }
    }

    public static void correctTest(ActionMapping mapping, 
                                 ActionForm form, 
                                 HttpServletRequest request) {
        if (request != null) {
            new UserActionCorrect().execute(mapping, form, request, null);
        }
    }
}