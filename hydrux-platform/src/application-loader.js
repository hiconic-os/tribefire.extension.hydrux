var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import * as tf from "../tribefire.js.tf-js-api-3.0~/tf-js-api.js";
import * as securityApiM from "../com.braintribe.gm.security-service-api-model-2.0~/ensure-security-service-api-model.js";
import * as hxApiM from "../tribefire.extension.hydrux.hydrux-api-model-2.1~/ensure-hydrux-api-model.js";
import { HxApplicationImpl, addToPath } from "./component-management.js";
const sessionIdCookieName = "tfsessionId";
(() => __awaiter(void 0, void 0, void 0, function* () {
    // TODO tfSettings should allow non-authorized applications
    const cookies = getCookies();
    const sessionId = cookies.get(sessionIdCookieName);
    if (!sessionId)
        return redirectToLogin();
    const connection = tf.remote.connect($tfUxHostSettings.servicesUrl);
    const sessionResponseMaybe = yield openSession();
    if (sessionResponseMaybe.isUnsatisfied()) {
        deleteCookie(sessionIdCookieName);
        return redirectToLogin();
    }
    const sessionResponse = sessionResponseMaybe.get();
    const servicesSession = connection.newSession(sessionResponse.userSession);
    const hxApplication = yield resolveHxApplication();
    if (!hxApplication)
        return;
    const theApp = new HxApplicationImpl(hxApplication, servicesSession);
    yield theApp.initAsync();
    $tf.hydrux = { application: theApp };
    const mainView = yield theApp.getRootScope().resolveView(hxApplication.view);
    const mainHtmlElement = mainView.htmlElement();
    mainHtmlElement.style.top = '0';
    mainHtmlElement.style.left = '0';
    mainHtmlElement.style.height = '100%';
    mainHtmlElement.style.width = '100%';
    mainHtmlElement.style.boxSizing = 'border-box';
    mainHtmlElement.style.position = 'relative';
    const body = document.body;
    body.appendChild(mainHtmlElement);
    /** Evaluates OpenUserSession for an existing sessionId from cookies and promises a response. */
    function openSession() {
        return __awaiter(this, void 0, void 0, function* () {
            const credentials = securityApiM.ExistingSessionCredentials.create();
            credentials.existingSessionId = sessionId;
            const openSession = securityApiM.OpenUserSession.create();
            openSession.credentials = credentials;
            return openSession.EvalAndGetReasoned(connection.evaluator());
        });
    }
    /** Evaluates ResolveHxApplication for domainId and useCases from the settings. */
    function resolveHxApplication() {
        return __awaiter(this, void 0, void 0, function* () {
            const request = hxApiM.ResolveHxApplication.create();
            request.targetDomainId = $tfUxHostSettings.domainId;
            request.prototypingModule = $tfUxHostSettings.prototypingModule;
            if ($tfUxHostSettings.usecases)
                request.useCases.addAllJs(...$tfUxHostSettings.usecases);
            const maybe = yield request.EvalAndGetReasoned(servicesSession.evaluator());
            if (!maybe.isSatisfied()) {
                console.error("No Hydrux app found for settings: " + JSON.stringify($tfUxHostSettings) + ". Reason: " + maybe.whyUnsatisfied().text);
                return null;
            }
            return maybe.value();
        });
    }
}))().catch(reason => {
    const body = document.body;
    body.innerHTML = "<span style='color:red'>Could not load Hydrux application. Error: " + reason + "</span>";
});
//
// Helpers
//
function redirectToLogin() {
    window.location.replace(addToPath($tfUxHostSettings.servicesUrl, "login?continue=" + encodeURIComponent(window.location.href)));
}
function getCookies() {
    const result = new Map();
    const ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        const c = ca[i].trim();
        const keyValue = c.split('=');
        const key = decodeURIComponent(keyValue[0]);
        const value = decodeURIComponent(keyValue[1]);
        result.set(key, value);
    }
    return result;
}
function deleteCookie(name) {
    // no idea if the path is correct
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}
