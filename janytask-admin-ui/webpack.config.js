// 输出目录
const __output_path = 'dist';

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
module.exports = {
    entry: {
        main: path.join(__dirname, 'main'),
    },
    resolve: {
        modules: ["node_modules", path.resolve(__dirname, "view")],
        extensions: ['.js', '.vue']
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }, {
                test: /\.html$/,
                loader: 'html-loader'
            }, {
                test: /\.js$/,
                loader: 'babel-loader'
            }, {
                test: /\.(jpg|png|git|svg|woff|woff2|ttf)$/,
                loader: 'url-loader'
            }, {
                test: /\.vue$/,
                loader: 'vue-loader'
            }
        ]
    },
    output: {
        path: path.join(__dirname, __output_path),
        filename: "bundle.js"
    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({template: path.join(__dirname, 'index.html')})
    ],
    devServer: {
        contentBase: path.join(__dirname, __output_path),
        compress: true,
        hot: true,
        port: 9000
    }
}