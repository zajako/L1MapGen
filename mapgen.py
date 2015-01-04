import struct
import sys
import os

#-----------------
# config variables
#-----------------
BINARY_DIR = '/Volumes/R1/Lineage/Tools/L1MapGen/data/'
bdir_l = len(BINARY_DIR)



def makepath(filename):
	dirname = os.path.dirname(filename)
	if not os.path.exists(dirname):
		umask = os.umask(0)
		os.makedirs(dirname, 0755)
		os.umask(umask)

def processMapFolder(root, name):
	fileList = []
	xList = []
	yList = []
	x_max = 0
	y_max = 0
	x_min = 999999999
	y_min = 999999999
	print os.path.join(root, name)
	mapPath = '%s' % os.path.join(BINARY_DIR, name)
	for file in os.listdir(mapPath):
		try:
			x = int(file[0:4],16)
			y = int(file[4:8],16)
			fileList.append(file)
			x_max = max(x, x_max)
			y_max = max(y, y_max)
			x_min = min(x, x_min)
			y_min = min(y, y_min)
			xList.append(x)
			yList.append(y)
			print '    x:{}  y:{}'.format(x,y)
		except ValueError:
			print "Could not convert data to an integer."

	xSize = xList[-1] - xList[0] + 1;
	ySize = yList[-1] - yList[0] + 1;

	print '  xSize: {} ySize: {}'.format(xSize,ySize)
	
	xSize2 = x_max - x_min + 1;
	ySize2 = y_max - y_min + 1;
	print '  xSize2: {} ySize2: {}'.format(xSize2,ySize2)

for root, dirs, files in os.walk(BINARY_DIR):
	for name in dirs:
		if name == '106' or name == '105' or name == '116':
			processMapFolder(root,name)

	#get MapFiles
	for filename in files:
		if not filename.startswith('.'):
			if filename.endswith('.map'):
				if filename.startswith('106'):
					up_file = os.path.join(root, filename)
					name = up_file[bdir_l:]
					#print 'Test: {}'.format(filename)