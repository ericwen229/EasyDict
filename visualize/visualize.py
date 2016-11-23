import os

for i in range(11):
    fileName = 'trie' + str(2 ** i)
    os.system('head -n ' + str(2 ** i + 1) + ' trie.dot > ' + fileName + '.dot')
    os.system('cat close.txt >> ' + fileName + '.dot')
    os.system('dot ' + fileName + '.dot' + ' -T png > ' + fileName + '.png')

