import axios from 'axios';
import querystring from 'querystring';

function Axios(options) {
    return new Promise(function (resolve, reject) {
        axios(options).then(response => {
            let rInfo = response.data;
            if (rInfo.code >= 0) {
                return resolve(rInfo);
            } else {
                if (rInfo.code === -401 && window.__vueFrame != null) {
                    window.__vueFrame.status = 1;
                }
                return reject(rInfo);
            }
        }).catch(error => {
            let response = error.response;
            return reject({
                code: -response.status,
                msg: error.message
            });
        });
    });
};

/**
 * GET获取
 * @param url
 * @param options
 */
Axios.get = function (url, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'GET';
    return Axios(internalOptions);
}

/**
 * POST提交
 * @param url
 * @param data
 * @param options
 */
Axios.post = function (url, data, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'POST';
    internalOptions.headers = internalOptions.headers || {};
    internalOptions.headers['Content-Type'] = 'application/x-www-form-urlencoded';
    internalOptions.data = querystring.stringify(data);
    return Axios(internalOptions);
}

/**
 * 使用JSON格式进行POST提交
 * @param url
 * @param data
 * @param options
 */
Axios.post2 = function (url, data, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'POST';
    internalOptions.headers = internalOptions.headers || {};
    internalOptions.headers['Content-Type'] = 'application/json';
    internalOptions.data = JSON.stringify(data);
    return Axios(internalOptions);
}

export default Axios;