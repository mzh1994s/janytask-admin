import axios from 'axios';

function Axios(options) {
    return new Promise(function (resolve, reject) {
        axios(options).then(response => {
            if (response.status === 200) {
                let rInfo = response.data;
                if (rInfo.code >= 0) {
                    return resolve(rInfo);
                } else {
                    return reject(rInfo);
                }
            } else {
                return reject({
                    code: -1,
                    msg: '网络错误'
                });
            }
        }).catch(response => {
            return reject({
                code: -1,
                msg: response
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
    internalOptions.data = data;
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