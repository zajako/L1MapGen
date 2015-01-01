L1JMapGen
--------------------

-これは何？-
V2Map実装時に書いたマップ生成ツールを公開用に書き直したものです。

-何が出来ますか？-
V1Map、V2Mapファイルの一括生成
TextMapReader用のMAP_INFO生成
調査用の全タイル値生成

-どのように使えば良いでしょう？-
1. ./config/config.propertiesを開き、LineageMapDirectoryを正しいパスに編集。
2. コマンドラインから、L1JMapGenディレクトリへ移動。
3. java -jar l1jmapgen.jar

-Eclipseで正しく開けません-
テスト用のライブラリが不足しているためです。
もしテストを動作させる必要があれば、以下のライブラリをlibディレクトリへ置いて下さい。
asm-3.3.1.jar
cglib-2.2.jar
easymock-3.0.jar
objenesis-1.2.jar
ツール本体のソースコードからは上記ライブラリは参照していない為、無くてもビルド可能です。

-ライセンス-
適当にどうぞ

-謝辞-
V1マップ生成にあたり、L1MapToolを参考にさせていただいた部分があります。
原作者の方にこの場を借りてお礼申し上げます。
