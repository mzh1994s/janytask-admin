import axios from 'axios';

function Http(options) {
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
Http.get = function (url, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'GET';
    return Http(internalOptions);
}

/**
 * POST提交
 * @param url
 * @param data
 * @param options
 */
Http.post = function (url, data, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'POST';
    internalOptions.data = data;
    return Http(internalOptions);
}

/**
 * 使用JSON格式进行POST提交
 * @param url
 * @param data
 * @param options
 */
Http.post2 = function (url, data, options) {
    let internalOptions = options || {};
    internalOptions.url = url;
    internalOptions.method = 'POST';
    internalOptions.headers = internalOptions.headers || {};
    internalOptions.headers['Content-Type'] = 'application/json';
    internalOptions.data = JSON.stringify(data);
    return Http(internalOptions);
}

export default Http;